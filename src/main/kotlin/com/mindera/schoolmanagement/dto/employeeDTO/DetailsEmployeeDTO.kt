package com.mindera.schoolmanagement.dto.employeeDTO

import com.mindera.schoolmanagement.Enumerator.EmployeeType
import com.mindera.schoolmanagement.dto.classroomDto.DetailsClassroomDTO
import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import javax.persistence.*

data class DetailsEmployeeDTO(
    var id: Long? = null,
    var name: String? = null,
    var age: Int? = null,
    var email: String? = null,
    var employeeType: EmployeeType? = null,
    var absenceEntities: List<Long?>? = null,
    var classroomID: Long? = null
)

fun convertToDetailsEmployeeDTO(entity: EmployeeEntity): DetailsEmployeeDTO {
    return DetailsEmployeeDTO(
        id = entity.id,
        name = entity.name,
        age = entity.age,
        email = entity.email,
        employeeType = entity.employeeType,
        absenceEntities = entity.absenceEntities?.map { it.id } ?: listOf(),
        classroomID = entity.classroomEntity?.id

    )
}