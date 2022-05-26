package com.mindera.schoolmanagement.service.absence

import com.mindera.schoolmanagement.dto.absence.CreateOrUpdateAbsenceDTO
import com.mindera.schoolmanagement.dto.absence.DetailsAbsenceDTO
import com.mindera.schoolmanagement.persistence.entity.AbsenceEntity
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
interface AbsenceService {

    fun createAbsence(dto: CreateOrUpdateAbsenceDTO): DetailsAbsenceDTO

}