package com.mindera.schoolmanagement.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoder {

    fun encode(password: String?): String {
        return BCryptPasswordEncoder().encode(password)
    }


    fun matches(password: String?, encodedPassword: String?): Boolean {
        return BCryptPasswordEncoder().matches(password, encodedPassword)
    }
}