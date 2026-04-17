package dev.nova.halserver.controller

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.RepresentationModel

open class GradeModel @JsonCreator constructor(
    @JsonProperty("student_id") val studentId: Int,
    @JsonProperty("teacher_id") val teacherId: Int,
    @JsonProperty("subject_id") val subjectId: Int,
    @JsonProperty("grade") val grade: Int,
): RepresentationModel<GradeModel>()