package com.pixapencil.server.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            csrf { disable() }
            cors { }

            addFilterBefore<BasicAuthenticationFilter>(TimeZoneFilter())

            authorizeHttpRequests {
                authorize(HttpMethod.POST, "/api/users/register", permitAll)
                authorize(HttpMethod.POST, "/api/users/verify", permitAll)
                authorize(HttpMethod.GET, "/api/creations/gallery", permitAll)
                authorize(anyRequest, authenticated)
            }

            httpBasic { }
        }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration =CorsConfiguration().apply {
                allowedOrigins = listOf("*")
                allowedMethods = listOf("GET", "POST", "PUT", "PATCH", "DELETE")
                applyPermitDefaultValues()
            }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/api/**", configuration)

        return source
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()
}
