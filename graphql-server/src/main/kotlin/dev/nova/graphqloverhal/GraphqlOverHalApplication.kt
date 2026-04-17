package dev.nova.graphqloverhal

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@SpringBootApplication
class GraphqlOverHalApplication

fun main(args: Array<String>) {
    runApplication<GraphqlOverHalApplication>(*args)
}

@Configuration
class RestClientConfig(
    @Value($$"${hal.server}") val halServer: String
) {

    @Bean
    fun buildRestClient(): RestClient {
        return RestClient.create(halServer)
    }
}

