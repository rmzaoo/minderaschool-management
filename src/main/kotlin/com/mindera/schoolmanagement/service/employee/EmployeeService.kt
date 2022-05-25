package com.mindera.schoolmanagement.service.employee

import com.mindera.schoolmanagement.dto.employeeDTO.CreateOrUpdateEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import com.mindera.schoolmanagement.persistence.entity.EmployeeEntity
import org.springframework.stereotype.Service

@Service
interface EmployeeService {

    fun getEmployees(): List<DetailsEmployeeDTO>

    fun getEmployeeById(id: Long): DetailsEmployeeDTO

    fun createEmployee(createOrUpdateEmployeeDTO: CreateOrUpdateEmployeeDTO): DetailsEmployeeDTO

}