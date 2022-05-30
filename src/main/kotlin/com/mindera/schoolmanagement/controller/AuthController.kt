package com.mindera.schoolmanagement.controller

import com.mindera.schoolmanagement.dto.absence.CreateOrUpdateAbsenceDTO
import com.mindera.schoolmanagement.dto.absence.DetailsAbsenceDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @PostMapping("/auth/login")
    fun login(@RequestBody login: String): ResponseEntity<String> {
        return ResponseEntity.ok(login)
    }

}