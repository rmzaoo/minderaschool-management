package com.mindera.schoolmanagement.dto.classroomDto

import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import org.springframework.stereotype.Component


class DetailsClassroomDTO(
    var name: String? = null,
    var teachers: List<EmployeeEntity>? = null,
    var students: List<EmployeeEntity>? = null
)


fun convertToClassroomEntity(dto: DetailsClassroomDTO): ClassroomEntity {
    val list = mutableListOf<EmployeeEntity>()
    list.addAll(dto.teachers!!)
    list.addAll(dto.students!!)
    return ClassroomEntity(
        name = dto.name,
        employeeEntities = list
    )
}

fun convertToDetailsClassroomDTO(entity: ClassroomEntity): DetailsClassroomDTO {
    return DetailsClassroomDTO(
        name = entity.name,
        teachers = entity.employeeEntities?.filter { it.employeeType.name == "TEACHER" }?.toList() ?: listOf(),
        students = entity.employeeEntities?.filter { it.employeeType.name == "STUDENT" }?.toList() ?: listOf()
    )
}

