package com.mindera.schoolmanagement.dto.auth

import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO

data class ValidLoginDTO(
    val token: String? = null,
    val employeeDetails: DetailsEmployeeDTO? = null
)