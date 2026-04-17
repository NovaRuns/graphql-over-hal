package dev.nova.halserver.controller

import dev.nova.halserver.repository.StudentRepository
import dev.nova.halserver.model.Student
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentRepository: StudentRepository,
) {

    @GetMapping
    fun findAllStudents(): List<StudentModel> {
        return studentRepository.findAll().map {
            it.toModel()
        }
    }

    @GetMapping("/{id}")
    fun findStudentById(
        @PathVariable("id") id: Int
    ): StudentModel {
        return studentRepository.findById(id).map {
            it.toModel()
        }.orElseThrow()
    }

    private fun Student.toModel(): StudentModel {
        val model = StudentModel(
            id = id,
            name = name
        )

        model.add(linkTo<GradeController> {
            findAllGrades(id)
        }.withRel("grades"))

        return model
    }
}