package com.mindera.schoolmanagement.service.absence

import com.mindera.schoolmanagement.dto.absence.CreateOrUpdateAbsenceDTO
import com.mindera.schoolmanagement.dto.absence.DetailsAbsenceDTO
import com.mindera.schoolmanagement.dto.absence.convertToAbsenceEntity
import com.mindera.schoolmanagement.dto.absence.convertToDetailsAbsenceDTO
import com.mindera.schoolmanagement.exception.exceptions.EmployeeNotFoundException
import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import com.mindera.schoolmanagement.persistence.repository.AbsenceRepository
import com.mindera.schoolmanagement.persistence.repository.EmployeeRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class AbsenceServiceImpl(
    private val absenceRepository: AbsenceRepository,
    private val employeeRepository: EmployeeRepository
) : AbsenceService {


    override fun createAbsence(dto: CreateOrUpdateAbsenceDTO): DetailsAbsenceDTO {
        val employee = employeeRepository.findById(dto.employeeId)
            .orElseThrow { throw EmployeeNotFoundException("Employee with id ${dto.employeeId} not found") }

        val absence = convertToAbsenceEntity(dto)
        absence.employeeEntity = employee

        return convertToDetailsAbsenceDTO(absenceRepository.save(absence))
    }


}