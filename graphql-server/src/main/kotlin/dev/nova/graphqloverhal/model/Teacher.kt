package dev.nova.graphqloverhal.model

data class Teacher(
    val id: Int = 0,
    val name: String
): HalModel()