package com.mindera.schoolmanagement.service.auth

import com.mindera.schoolmanagement.dto.auth.LoginUserDTO
import com.mindera.schoolmanagement.dto.auth.ValidLoginDTO
import com.mindera.schoolmanagement.dto.employeeDTO.convertToDetailsEmployeeDTO
import com.mindera.schoolmanagement.exception.exceptions.EmployeeNotFoundException
import com.mindera.schoolmanagement.persistence.repository.EmployeeRepository
import com.mindera.schoolmanagement.util.PasswordEncoder
import com.mindera.schoolmanagement.util.TokenGenerator
import org.springframework.stereotype.Service


@Service
class AuthServiceImpl(private val employeeRepository: EmployeeRepository) : AuthService {

    override fun login(loginUserDTO: LoginUserDTO): ValidLoginDTO {
        val employee = employeeRepository.findByEmail(loginUserDTO.email!!)
            .orElseThrow { throw EmployeeNotFoundException("Employee with email ${loginUserDTO.email} not found") }

        PasswordEncoder().matches(loginUserDTO.password, employee.password).let {
            if (!it) throw EmployeeNotFoundException("Employee with email ${loginUserDTO.email} not found")
        }

        val detailsEmployeeDTO = convertToDetailsEmployeeDTO(employee)

        return ValidLoginDTO(
            token = TokenGenerator().generateToken(detailsEmployeeDTO),
            employeeDetails = detailsEmployeeDTO
        )
    }

}