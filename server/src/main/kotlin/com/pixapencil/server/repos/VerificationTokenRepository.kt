package com.pixapencil.server.repos

import com.pixapencil.server.domain.User
import com.pixapencil.server.domain.VerificationToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface VerificationTokenRepository : JpaRepository<VerificationToken, Long> {
    fun findByUser(user: User): VerificationToken?

    @Modifying
    @Query("DELETE FROM VerificationToken t WHERE t.expiryDate < ?1")
    fun deleteExpiredTokens(expiryDate: LocalDateTime)
}
