package dev.nova.graphqloverhal.model

data class Student(
    val id: Int = 0,
    val name: String
): HalModel()