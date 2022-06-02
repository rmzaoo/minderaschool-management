package com.mindera.schoolmanagement.controller

import com.mindera.schoolmanagement.dto.absence.DetailsAbsenceDTO
import com.mindera.schoolmanagement.dto.employeeDTO.CreateOrUpdateEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import com.mindera.schoolmanagement.security.IsTeacher
import com.mindera.schoolmanagement.service.employee.EmployeeService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class EmployeeController(private val employeeService: EmployeeService) {

    @GetMapping("/employees")
    @IsTeacher
    fun getAllEmployees(): ResponseEntity<List<DetailsEmployeeDTO>> {
        return ResponseEntity.ok(employeeService.getEmployees())
    }

    @GetMapping("/employees/{id}")
    fun getAllEmployees(@PathVariable id: Long): ResponseEntity<DetailsEmployeeDTO> {
        return ResponseEntity.ok(employeeService.getEmployeeById(id))
    }

    @PostMapping("/employees")
    fun createEmployee(@RequestBody createOrUpdateEmployeeDTO: CreateOrUpdateEmployeeDTO): ResponseEntity<DetailsEmployeeDTO> {
        return ResponseEntity.ok(employeeService.createEmployee(createOrUpdateEmployeeDTO))
    }

    @PutMapping("/employees/{id}/classroom/{classroomId}")
    fun updateClassroom(@PathVariable id: Long, @PathVariable classroomId: Long): ResponseEntity<DetailsEmployeeDTO> =
        ResponseEntity.ok(employeeService.updateEmployeeClassroom(id, classroomId))

    @GetMapping("/employees/{id}/absences")
    fun getAbsenceBetweenDates(
        @PathVariable id: Long,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate?,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate?
    ): ResponseEntity<List<DetailsAbsenceDTO>> {
        startDate
            ?: return ResponseEntity.ok(employeeService.getEmployeeAbsences(id)) // if startDate is null, return all absences

        return ResponseEntity.ok(employeeService.getEmployeeAbsencesBetweenDates(id, startDate, endDate ?: startDate))

    }
}