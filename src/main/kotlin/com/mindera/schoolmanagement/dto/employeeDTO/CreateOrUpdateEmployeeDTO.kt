package com.mindera.schoolmanagement.dto.employeeDTO

import com.mindera.schoolmanagement.Enumerator.EmployeeType
import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity

data class CreateOrUpdateEmployeeDTO(
    var name: String? = null,
    var age: Int? = null,
    var email: String? = null,
    var password: String? = null,
    var employeeType: EmployeeType? = null
)


fun convertToEmployeeEntity(dto: CreateOrUpdateEmployeeDTO): EmployeeEntity {
    return EmployeeEntity(
        name = dto.name,
        age = dto.age,
        email = dto.email,
        password = dto.password,
        employeeType = dto.employeeType,
    )
}

