package dev.nova.halserver.repository

import dev.nova.halserver.model.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository : JpaRepository<Subject, Int>