package dev.nova.halserver.model

import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "grades")
data class Grade(
    @EmbeddedId
    val id: GradeId,
    val gradeValue: Int
)

@Embeddable
data class GradeId(
    val studentId: Int,
    val teacherId: Int,
    val subjectId: Int
) : Serializable
