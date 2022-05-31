package com.mindera.schoolmanagement.util

import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.util.*


@ConfigurationProperties
@Configuration
class TokenGenerator {

    private var secret: String = System.getenv("JWT_SECRET") ?: "noSafeSecretToken"
    private var expiration: String = "24"

    fun generateToken(detailsEmployeeDTO: DetailsEmployeeDTO): String? =
        Jwts.builder()
            .setSubject("Employee Details")
            .addClaims(detailsEmployeeDTO.toMap())
            .setExpiration(generateExpirationDate())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()


    fun validateToken(token: String?): Boolean =
        Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).body.subject == "Employee Details"

    private fun generateExpirationDate(): Date =
        Date(System.currentTimeMillis() + expiration.toLong() * 1000)
}