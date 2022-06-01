package com.mindera.schoolmanagement.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration() : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .cors()
            .and()
            .addFilterBefore(CookieAuthFilter(), BasicAuthenticationFilter::class.java)
            .addFilterBefore(JWTAuthorizationFilter(), CookieAuthFilter::class.java)
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/actuator/**").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .antMatchers("/swagger-ui.html", "/swagger-ui/*", "/v3/api-docs", "/v3/api-docs/*").permitAll()
            .anyRequest().authenticated()

    }

}



fun setUpSpringAuthentication(claims: Claims) {
    val authorities = claims["authorities"]


    val auth = UsernamePasswordAuthenticationToken(
        "user", null,
        listOf(SimpleGrantedAuthority("ROLE_TEACHER"))
    )

    SecurityContextHolder.getContext().authentication = auth
}