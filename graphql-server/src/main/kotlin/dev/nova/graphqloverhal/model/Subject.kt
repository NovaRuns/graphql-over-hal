package dev.nova.graphqloverhal.model

data class Subject(
    val id: Int = 0,
    val name: String
): HalModel()