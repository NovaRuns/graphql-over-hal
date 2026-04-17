package dev.nova.halserver.controller

import dev.nova.halserver.repository.GradeRepository
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/grades")
class GradeController(
    private val gradeRepository: GradeRepository,
) {

    @GetMapping
    fun findAllGrades(
        @RequestParam(name = "studentId", required = false) studentId: Int? = null
    ): List<GradeModel> {
        val grades = if(studentId == null) gradeRepository.findAll() else gradeRepository.findAllById_StudentId(
            studentId
        )

        return grades.map {
            val model = GradeModel(
                studentId = it.id.studentId,
                teacherId = it.id.teacherId,
                subjectId = it.id.subjectId,
                grade = it.gradeValue
            )

            model.add(linkTo<StudentController> { findStudentById(it.id.studentId) }.withRel("student"))
            model.add(linkTo<TeacherController> { findTeacherById(it.id.teacherId) }.withRel("teacher"))
            model.add(linkTo<SubjectController> { findSubjectById(it.id.subjectId) }.withRel("subject"))

            model
        }
    }
}