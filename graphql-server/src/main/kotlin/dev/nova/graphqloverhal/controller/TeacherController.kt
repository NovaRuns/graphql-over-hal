package dev.nova.graphqloverhal.controller

import dev.nova.graphqloverhal.model.Teacher
import org.springframework.core.ParameterizedTypeReference
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.client.RestClient

@Controller
class TeacherController(
    val restClient: RestClient
) {

    @QueryMapping
    fun teachers(): List<Teacher> {
        return restClient.get()
            .uri("/teachers")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<Teacher>>() {})!!
    }

    @QueryMapping
    fun teacher(@Argument id: Int): Teacher? {
        return restClient.get()
            .uri("/teachers/$id")
            .retrieve()
            .body(Teacher::class.java)
    }
}