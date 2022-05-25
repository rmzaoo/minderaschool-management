package com.mindera.schoolmanagement.dto.employeeDTO

import com.mindera.schoolmanagement.Enumerator.EmployeeType
import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity

class CreateOrUpdateEmployeeDTO(
    var name: String? = null,
    var age: Int? = null,
    var email: String? = null,
    var employeeType: EmployeeType = EmployeeType.Student,
)


