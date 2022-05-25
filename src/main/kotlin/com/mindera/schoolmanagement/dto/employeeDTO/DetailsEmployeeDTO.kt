package com.mindera.schoolmanagement.dto.employeeDTO

import com.mindera.schoolmanagement.Enumerator.EmployeeType
import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.entity.ClassroomEntity
import javax.persistence.*

class DetailsEmployeeDTO(
    var id: Long? = null,
    var name: String? = null,
    var age: Int? = null,
    var email: String? = null,
    var employeeType: EmployeeType = EmployeeType.Student,
    var absenceEntities: List<AbsenceEntity>? = null,
    var classroomEntity: ClassroomEntity? = null
)