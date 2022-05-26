package com.mindera.schoolmanagement.controller

import com.mindera.schoolmanagement.dto.absence.DetailsAbsenceDTO
import com.mindera.schoolmanagement.dto.employeeDTO.CreateOrUpdateEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import com.mindera.schoolmanagement.service.employee.EmployeeService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
class EmployeeController(private val employeeService: EmployeeService){

    @GetMapping("/employees")
    fun getAllEmployees(): ResponseEntity<List<DetailsEmployeeDTO>> {
        return ResponseEntity.ok(employeeService.getEmployees())
    }

    @GetMapping("/employees/{id}")
    fun getAllEmployees(@PathVariable id : Long): ResponseEntity<DetailsEmployeeDTO> {
        return ResponseEntity.ok(employeeService.getEmployeeById(id))
    }

    @PostMapping("/employee")
    fun createEmployee(@RequestBody createOrUpdateEmployeeDTO: CreateOrUpdateEmployeeDTO) : ResponseEntity<DetailsEmployeeDTO> {
        return ResponseEntity.ok(employeeService.createEmployee(createOrUpdateEmployeeDTO))
    }

    @PutMapping("/employee/{id}/classroom/{classroomId}")
    fun updateClassroom(@PathVariable id: Long, @PathVariable classroomId: Long): ResponseEntity<DetailsEmployeeDTO>{
        return ResponseEntity.ok(employeeService.updateEmployeeClassroom(id, classroomId))
    }

    @GetMapping("/employee/{id}/absences/")
    fun getAbsence(@PathVariable id: Long): ResponseEntity<List<DetailsAbsenceDTO>> {
        return ResponseEntity.ok(employeeService.getAbsences(id))
    }

    @GetMapping("/employee/{id}/absences/dates")
    fun getAbsenceBetweenDates(
        @PathVariable id: Long,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate?
    ): ResponseEntity<List<DetailsAbsenceDTO>> {
        return ResponseEntity.ok(employeeService.getEmployeeAbsencesBetweenDates(id, startDate, endDate ?: startDate))
    }
}