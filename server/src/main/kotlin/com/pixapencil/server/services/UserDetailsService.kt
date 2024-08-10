package com.pixapencil.server.services

import com.pixapencil.server.repos.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): AuthUser {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User $username not found")

        return AuthUser(user)
    }
}
