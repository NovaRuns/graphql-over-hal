package dev.nova.graphqloverhal.controller

import dev.nova.graphqloverhal.model.Subject
import org.springframework.core.ParameterizedTypeReference
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.client.RestClient

@Controller
class SubjectController(
    val restClient: RestClient
) {

    @QueryMapping
    fun subjects(): List<Subject> {
        return restClient.get()
            .uri("/subjects")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<Subject>>() {})!!
    }

    @QueryMapping
    fun subject(@Argument id: Int): Subject? {
        return restClient.get()
            .uri("/subjects/$id")
            .retrieve()
            .body(Subject::class.java)
    }
}