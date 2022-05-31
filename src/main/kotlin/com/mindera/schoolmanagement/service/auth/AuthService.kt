package com.mindera.schoolmanagement.service.auth

import com.mindera.schoolmanagement.dto.auth.LoginUserDTO
import com.mindera.schoolmanagement.dto.auth.ValidLoginDTO
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import org.springframework.security.core.userdetails.User

interface AuthService {

    fun login(loginUserDTO: LoginUserDTO): ValidLoginDTO
}