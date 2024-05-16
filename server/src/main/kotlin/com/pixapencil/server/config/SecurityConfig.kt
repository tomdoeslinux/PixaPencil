package com.pixapencil.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            csrf { disable() }

            authorizeHttpRequests {
//                authorize(HttpMethod.POST,"/api/users/register", permitAll)
//                authorize(HttpMethod.POST,"/api/users/verify", permitAll)
                authorize(anyRequest, permitAll)
            }

            httpBasic { }
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder(12)
}