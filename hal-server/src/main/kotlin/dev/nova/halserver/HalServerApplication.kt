package dev.nova.halserver

import dev.nova.halserver.model.Grade
import dev.nova.halserver.model.GradeId
import dev.nova.halserver.model.Student
import dev.nova.halserver.model.Subject
import dev.nova.halserver.model.Teacher
import dev.nova.halserver.repository.GradeRepository
import dev.nova.halserver.repository.StudentRepository
import dev.nova.halserver.repository.SubjectRepository
import dev.nova.halserver.repository.TeacherRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component


@SpringBootApplication
class HalServerApplication

fun main(args: Array<String>) {
    runApplication<HalServerApplication>(*args)
}


@Component
class Runner(
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository,
    private val subjectRepository: SubjectRepository,
    private val gradeRepository: GradeRepository,
) : CommandLineRunner {

    override fun run(vararg args: String) {
        val john = studentRepository.save(Student(name = "John"))
        val tom = studentRepository.save(Student(name = "Tom"))
        val michael = studentRepository.save(Student(name = "Michael"))

        val charles = teacherRepository.save(Teacher(name = "Charles")) // teaches math
        val eric = teacherRepository.save(Teacher(name = "Eric")) // examines math, teaches geography
        val jane = teacherRepository.save(Teacher(name = "Jane")) // examines geography

        val math = subjectRepository.save(Subject(name = "Math"))
        val geography = subjectRepository.save(Subject(name = "Geography"))

        eric.examines(john, math, 9)
        eric.examines(tom, math, 7)
        eric.examines(michael, math, 10)

        jane.examines(john, geography, 5)
        jane.examines(tom, geography, 10)
        jane.examines(michael, geography, 7)
    }

    private fun Teacher.examines(student: Student, subject: Subject, grade: Int) {
        gradeRepository.save(
            Grade(
                id = GradeId(
                    studentId = student.id,
                    teacherId = id,
                    subjectId = subject.id,
                ), gradeValue = grade
            )
        )
    }

}
