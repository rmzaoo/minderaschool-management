package com.mindera.schoolmanagement.dto.classroomDto

import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import java.util.stream.Collectors


data class DetailsClassroomDTO(
    var id: Long? = null,
    var name: String? = null,
    var teachers: List<Long?>? = null,
    var students: List<Long?>? = null
)


fun convertToDetailsClassroomDTO(entity: ClassroomEntity): DetailsClassroomDTO {
    val teachers = entity.employeeEntities?.stream()?.filter { it.employeeType?.name == "Teacher" }?.map { it.id }
        ?.collect(Collectors.toList())
    val students = entity.employeeEntities?.stream()?.filter { it.employeeType?.name == "Student" }?.map { it.id }
        ?.collect(Collectors.toList())


    return DetailsClassroomDTO(
        id = entity.id,
        name = entity.name,
        teachers = teachers,
        students = students
    )
}


