package dev.nova.graphqloverhal.controller

import dev.nova.graphqloverhal.model.Grade
import dev.nova.graphqloverhal.model.Student
import dev.nova.graphqloverhal.model.Subject
import dev.nova.graphqloverhal.model.Teacher
import org.springframework.core.ParameterizedTypeReference
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import org.springframework.web.client.RestClient

@Controller
class GradeController(
    val restClient: RestClient
) {

    @QueryMapping
    fun grades(@Argument studentId: Int?): List<Grade> {
        var url = "/grades"
        if(studentId != null) {
            url += "?studentId=$studentId"
        }
        return restClient.get()
            .uri(url)
            .retrieve()
            .body(object : ParameterizedTypeReference<List<Grade>>() {})!!
    }
}

@Controller
class GradeFieldResolver(
    private val restClient: RestClient
) {

    @SchemaMapping(typeName = "Grade", field = "student")
    fun student(grade: Grade): Student? {
        return restClient.get()
            .uri(grade.links.filter { it.rel == "student" }.map { it.href }.first())
            .retrieve()
            .body(Student::class.java)
    }

    @SchemaMapping(typeName = "Grade", field = "teacher")
    fun teacher(grade: Grade): Teacher? {
        return restClient.get()
            .uri(grade.links.filter { it.rel == "teacher" }.map { it.href }.first())
            .retrieve()
            .body(Teacher::class.java)
    }

    @SchemaMapping(typeName = "Grade", field = "subject")
    fun subject(grade: Grade): Subject? {
        return restClient.get()
            .uri(grade.links.filter { it.rel == "subject" }.map { it.href }.first())
            .retrieve()
            .body(Subject::class.java)
    }

    @SchemaMapping(typeName = "Grade", field = "text")
    fun text(grade: Grade): String? = "${teacher(grade)!!.name} marked student ${student(grade)!!.name} with ${grade.grade} for ${subject(grade)!!.name}"
}