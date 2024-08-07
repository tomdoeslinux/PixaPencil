package com.pixapencil.server.repos

import com.pixapencil.server.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    fun findByUsername(username: String): User?
}
