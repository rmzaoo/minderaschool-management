package com.mindera.schoolmanagement.util

import com.mindera.schoolmanagement.dto.employeeDTO.DetailsEmployeeDTO
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import java.util.*
import java.util.stream.Collectors


@ConfigurationProperties
@Configuration
class TokenGenerator {

    private var secret: String = System.getenv("JWT_SECRET") ?: "noSafeSecretToken"
    private var expiration: String = "24"

    fun generateToken(detailsEmployeeDTO: DetailsEmployeeDTO): String? {
        val claims = HashMap<String, Any>()
        return Jwts.builder()
            .setId("minderaschool-management")
            .setSubject(detailsEmployeeDTO.email)
            .claim("authorities", "ROLE_"+detailsEmployeeDTO.employeeType!!.name.uppercase())
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 600000))
            .signWith(
                SignatureAlgorithm.HS512,
                secret
            ).compact()

    }

//
//    fun validateToken(token: String?): Boolean =
//        Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).body.subject == "Employee Details"
//
//    fun getIdFromToken(token: String?): String? {
//        val body = Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).body
//        return body.subject
//    }



}