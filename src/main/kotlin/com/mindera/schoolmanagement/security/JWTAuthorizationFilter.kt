package com.mindera.schoolmanagement.security

import io.jsonwebtoken.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter : OncePerRequestFilter() {

    private val HEADER = "Authorization"
    private val PREFIX = "Bearer "
    private val SECRET = System.getenv("JWT_SECRET") ?: "noSafeSecretToken"


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            if (checkJWTToken(request, response)) {
                val claims: Claims = validateToken(request)
                if (claims["authorities"] != null) {
                    setUpSpringAuthentication(claims)
                } else {
                    SecurityContextHolder.clearContext()
                }
            } else {
                SecurityContextHolder.clearContext()
            }
            filterChain.doFilter(request, response)
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


    private fun validateToken(request: HttpServletRequest): Claims {
        val jwtToken: String = request.getHeader(HEADER).replace(PREFIX, "")
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken).body
    }


    private fun setUpSpringAuthentication(claims: Claims) {
        val authorities = claims["authorities"]


        val auth = UsernamePasswordAuthenticationToken(
            "user", null,
            listOf(SimpleGrantedAuthority("ROLE_TEACHER"))
        )

        SecurityContextHolder.getContext().authentication = auth
    }

    private fun checkJWTToken(request: HttpServletRequest, res: HttpServletResponse): Boolean {
        val authenticationHeader = request.getHeader(HEADER)
        return !(authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
    }
}