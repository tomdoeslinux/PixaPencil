package com.pixapencil.server.repos

import com.pixapencil.server.domain.User
import com.pixapencil.server.domain.VerificationToken
import org.springframework.data.jpa.repository.JpaRepository

interface VerificationTokenRepository : JpaRepository<VerificationToken, Long> {
    fun findByUser(user: User): VerificationToken?
}
