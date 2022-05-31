package com.mindera.schoolmanagement.security

import com.mindera.schoolmanagement.service.auth.AuthServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration(private val authServiceImpl: AuthServiceImpl) :
    WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http
            .csrf().disable()
            .addFilterBefore(siteminderFilter(), RequestHeaderAuthenticationFilter::class.java)
            .authorizeRequests()
            .antMatchers("/actuator/**").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/swagger-ui.html", "/swagger-ui/*", "/v3/api-docs", "/v3/api-docs/*").permitAll()
            .anyRequest().denyAll()


//        http
//            .cors()
//            .and()
//            .addFilterBefore(
//                siteminderFilter(),
//                RequestHeaderAuthenticationFilter::class.java
//            )
//            .csrf().and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeRequests()
//            .antMatchers(HttpMethod.POST, "/auth/login")
//            .permitAll()
//            .antMatchers("/swagger-ui.html", "/swagger-ui/*", "/v3/api-docs", "/v3/api-docs/*")
//            .permitAll()
//            .anyRequest().authenticated()


//        http.csrf().disable().authorizeRequests().anyRequest().permitAll()

    }


    fun siteminderFilter(): RequestHeaderAuthenticationFilter? {
        val requestHeaderAuthenticationFilter = RequestHeaderAuthenticationFilter()

        // set the header name which provides principal information
        requestHeaderAuthenticationFilter.setPrincipalRequestHeader("x-actor-id")

        // set the header name which provides credential information
        requestHeaderAuthenticationFilter.setCredentialsRequestHeader("x-grantor-id")
        requestHeaderAuthenticationFilter.setAuthenticationManager(authenticationManager())

        // do not throw exception when header is not present.
        // one use case is for actuator endpoints and static assets where security headers are not required.
        requestHeaderAuthenticationFilter.setExceptionIfHeaderMissing(false)
        return requestHeaderAuthenticationFilter
    }

}