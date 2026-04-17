package dev.nova.graphqloverhal.model

data class Grade(
    val student_id: Int,
    val teacher_id: Int,
    val subject_id: Int,
    val grade: Int
): HalModel()