package com.mindera.schoolmanagement.security

import com.mindera.schoolmanagement.service.auth.AuthService
import com.mindera.schoolmanagement.service.auth.AuthServiceImpl
import com.mindera.schoolmanagement.util.TokenGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration(private val authServiceImpl: AuthServiceImpl) :
    WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
//        http
//            .cors()
//            .and()
//            .csrf()
//            .and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeRequests()
//            .antMatchers("/swagger-ui.html", "/swagger-ui/*", "/v3/api-docs", "/v3/api-docs/*").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic().disable()

        http.csrf().disable().authorizeRequests().anyRequest().permitAll()

    }

}