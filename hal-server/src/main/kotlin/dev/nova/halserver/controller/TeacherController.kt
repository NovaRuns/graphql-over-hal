package dev.nova.halserver.controller

import dev.nova.halserver.repository.TeacherRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teachers")
class TeacherController(
    private val teacherRepository: TeacherRepository,
) {

    @GetMapping
    fun findAllTeachers(): List<TeacherModel> {
        return teacherRepository.findAll().map {
            TeacherModel(
                id = it.id,
                name = it.name
            )
        }
    }

    @GetMapping("/{id}")
    fun findTeacherById(
        @PathVariable("id") id: Int
    ): TeacherModel {
        return teacherRepository.findById(id).map {
            TeacherModel(
                id = it.id,
                name = it.name
            )
        }.orElseThrow()
    }
}