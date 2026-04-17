package dev.nova.graphqloverhal.controller

import dev.nova.graphqloverhal.model.Student
import org.springframework.core.ParameterizedTypeReference
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.client.RestClient

@Controller
class StudentController(
    val restClient: RestClient
) {

    @QueryMapping
    fun students(): List<Student> {
        return restClient.get()
            .uri("/students")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<Student>>() {})!!
    }

    @QueryMapping
    fun student(@Argument id: Int): Student? {
        return restClient.get()
            .uri("/students/$id")
            .retrieve()
            .body(Student::class.java)
    }
}