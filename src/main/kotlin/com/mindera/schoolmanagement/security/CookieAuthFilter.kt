package com.mindera.schoolmanagement.security

import io.jsonwebtoken.*
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class CookieAuthFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val cookies = request.cookies

        if (cookies != null) {
            val token = Arrays.stream(cookies)
                .filter { cookie -> cookie.name == "Authorization" }
                .map { cookie -> cookie.value }
                .collect(Collectors.toList())
                .firstOrNull()

            try {
                if (token != null) {
                    val claims: Claims = validateToken(token)
                    setUpSpringAuthentication(claims)
                }
            } catch (e: ExpiredJwtException) {
                logger.error("JWT Token is expired")
                response.status = HttpServletResponse.SC_FORBIDDEN
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
                return
            } catch (e: UnsupportedJwtException) {
                logger.error("JWT Token is unsupported")
                response.status = HttpServletResponse.SC_FORBIDDEN
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
                return
            } catch (e: MalformedJwtException) {
                logger.error("JWT Token is malformed")
                response.status = HttpServletResponse.SC_FORBIDDEN
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
                return
            } catch (e: SignatureException) {
                logger.error("JWT Token has invalid signature")
                response.status = HttpServletResponse.SC_FORBIDDEN
                response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
                return
            }
        }

        filterChain.doFilter(request, response)
    }


    private fun validateToken(jwtToken: String): Claims {
        val jwtSecret = System.getenv("JWT_SECRET") ?: "noSafeSecretToken"
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).body
    }

}