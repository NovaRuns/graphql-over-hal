package dev.nova.halserver.repository

import dev.nova.halserver.model.Teacher
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherRepository : JpaRepository<Teacher, Int>