package dev.nova.halserver.repository

import dev.nova.halserver.model.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Int>