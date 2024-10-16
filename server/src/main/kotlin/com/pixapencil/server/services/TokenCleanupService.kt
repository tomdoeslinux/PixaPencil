package com.pixapencil.server.services

import com.pixapencil.server.repos.VerificationTokenRepository
import jakarta.transaction.Transactional
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Transactional
@Service
class TokenCleanupService(private val tokenRepository: VerificationTokenRepository) {

    @Scheduled(cron = "0 0 1 * * ?")
    fun cleanup() {
        tokenRepository.deleteExpiredTokens(LocalDateTime.now())
    }
}