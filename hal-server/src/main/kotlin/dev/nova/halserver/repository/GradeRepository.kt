package dev.nova.halserver.repository

import dev.nova.halserver.model.Grade
import dev.nova.halserver.model.GradeId
import org.springframework.data.jpa.repository.JpaRepository

interface GradeRepository : JpaRepository<Grade, GradeId> {
    fun findAllById_StudentId(studentId: Int) : List<Grade>
}