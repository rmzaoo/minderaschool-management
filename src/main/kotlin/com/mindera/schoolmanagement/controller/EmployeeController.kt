package com.mindera.schoolmanagement.controller

import com.mindera.schoolmanagement.dto.employeeDTO.CreateOrUpdateEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import com.mindera.schoolmanagement.service.employee.EmployeeService
import lombok.extern.slf4j.Slf4j
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
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
}