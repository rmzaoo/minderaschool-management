package com.mindera.schoolmanagement.controller

import com.mindera.schoolmanagement.dto.auth.LoginUserDTO
import com.mindera.schoolmanagement.dto.auth.ValidLoginDTO
import com.mindera.schoolmanagement.service.auth.AuthService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(private val authService: AuthService) {

    @PostMapping("/auth/login")
    fun login(@RequestBody loginUserDTO: LoginUserDTO): ResponseEntity<ValidLoginDTO> {
        val validLoginDTO = authService.login(loginUserDTO)

        val responseCookie = ResponseCookie
            .from("Authorization", validLoginDTO.token!!)
            .httpOnly(true)
            .secure(false)
            .maxAge(-1)
            .build()


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(validLoginDTO)

//        return ResponseEntity.ok(authService.login(loginUserDTO))
    }
}