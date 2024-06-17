package com.pixapencil.server

import com.pixapencil.server.repos.VerificationTokenRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@EnableScheduling
@SpringBootApplication
class ServerApplication

@Component
class TokenCleanupService(private val tokenRepository: VerificationTokenRepository) {

    @Scheduled(cron = "0 0 1 * * ?")
    fun cleanup() {
        tokenRepository.deleteExpiredTokens(LocalDateTime.now())
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
