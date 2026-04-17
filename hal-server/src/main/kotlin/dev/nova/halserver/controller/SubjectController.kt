package dev.nova.halserver.controller

import dev.nova.halserver.repository.SubjectRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/subjects")
class SubjectController(
    private val subjectRepository: SubjectRepository,
) {

    @GetMapping
    fun findAllSubjects(): List<SubjectModel> {
        return subjectRepository.findAll().map {
            SubjectModel(
                id = it.id,
                name = it.name
            )
        }
    }

    @GetMapping("/{id}")
    fun findSubjectById(
        @PathVariable("id") id: Int
    ): SubjectModel {
        return subjectRepository.findById(id).map {
            SubjectModel(
                id = it.id,
                name = it.name
            )
        }.orElseThrow()
    }
}