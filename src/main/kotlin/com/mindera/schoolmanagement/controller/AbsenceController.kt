package com.mindera.schoolmanagement.controller

import com.mindera.schoolmanagement.dto.absence.CreateOrUpdateAbsenceDTO
import com.mindera.schoolmanagement.dto.absence.DetailsAbsenceDTO
import com.mindera.schoolmanagement.service.absence.AbsenceService
import org.springframework.data.jpa.repository.Query
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
class AbsenceController(private val absenceService: AbsenceService) {

    @PostMapping("/absences/")
    fun createAbsence(@RequestBody createOrUpdateAbsenceDTO: CreateOrUpdateAbsenceDTO): ResponseEntity<DetailsAbsenceDTO> {
        return ResponseEntity.ok(absenceService.createAbsence(createOrUpdateAbsenceDTO))
    }

}