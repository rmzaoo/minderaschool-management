package com.mindera.schoolmanagement.service.employee

import com.mindera.schoolmanagement.dto.employeeDTO.CreateOrUpdateEmployeeDTO
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import com.mindera.schoolmanagement.persistence.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl(private val employeeRepository: EmployeeRepository) : EmployeeService {

    override fun getEmployees(): List<DetailsEmployeeDTO> {
        TODO("Not yet implemented")
    }

    override fun getEmployeeById(id: Long): DetailsEmployeeDTO {
        TODO("Not yet implemented")
    }

    override fun createEmployee(createOrUpdateEmployeeDTO: CreateOrUpdateEmployeeDTO): DetailsEmployeeDTO {
        TODO("Not yet implemented")
    }

}
