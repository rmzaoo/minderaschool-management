package com.mindera.schoolmanagement.controller

import com.mindera.schoolmanagement.dto.auth.LoginUserDTO
import com.mindera.schoolmanagement.dto.auth.ValidLoginDTO
import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import com.mindera.schoolmanagement.service.auth.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class AuthController (private val authService: AuthService) {

    @PostMapping("/auth/login")
    fun login(@RequestBody loginUserDTO: LoginUserDTO): ResponseEntity<ValidLoginDTO> {
        return ResponseEntity.ok(authService.login(loginUserDTO))
    }

}