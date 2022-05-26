package com.mindera.schoolmanagement.dto.absence

import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import java.time.LocalDate

data class DetailsAbsenceDTO(
    var id: Long? = null,
    var employeeId: Long? = null,
    var date: LocalDate? = null,
    var reason: String? = null
)

fun convertToDetailsAbsenceDTO(entity: AbsenceEntity): DetailsAbsenceDTO {
    return DetailsAbsenceDTO(
        id = entity.id,
        employeeId = entity.employeeEntity?.id,
        date = entity.date,
        reason = entity.reason
    )
}


