package com.mindera.schoolmanagement.security

import com.mindera.schoolmanagement.service.auth.AuthServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration() : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .cors()
            .and()
            .csrf().disable()
            .addFilterBefore(JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .authorizeRequests()
            .antMatchers("/actuator/**").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .antMatchers("/swagger-ui.html", "/swagger-ui/*", "/v3/api-docs", "/v3/api-docs/*").permitAll()
            .anyRequest().authenticated()

    }

}