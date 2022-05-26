package com.mindera.schoolmanagement.dto.absence

import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import java.time.LocalDate

data class CreateOrUpdateAbsenceDTO(
    var employeeId: Long,
    var date: LocalDate? = null,
    var reason: String? = null
)


fun convertToAbsenceEntity(dto: CreateOrUpdateAbsenceDTO): AbsenceEntity {
    return AbsenceEntity(
        date = dto.date,
        reason = dto.reason
    )
}