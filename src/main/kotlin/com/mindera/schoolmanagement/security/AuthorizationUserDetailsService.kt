package com.mindera.schoolmanagement.security

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import java.util.*


class AuthorizationUserDetailsService : AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    override fun loadUserDetails(token: PreAuthenticatedAuthenticationToken?): UserDetails {
        val principal = token?.principal as String
        val credential = token.credentials as String

        // TODO this is only for illustration purpose. Should retrieve user from data store and determine user roles
        println("==================== $token")

        return if (principal == "joe") {
            // TODO some user lookup and then create User object with roles
            User("admin-user", "", Collections.singletonList(SimpleGrantedAuthority("ROLE_TEACHER")))
        } else {
            User("normal-user", "", Collections.singletonList(SimpleGrantedAuthority("ROLE_STUDENT")))
        }


    }

}