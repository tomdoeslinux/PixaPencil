package com.pixapencil.server.services

import com.pixapencil.server.domain.User
import com.pixapencil.server.domain.VerificationToken
import com.pixapencil.server.exceptions.EmailAlreadyInUseException
import com.pixapencil.server.repos.UserRepository
import com.pixapencil.server.repos.VerificationTokenRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime
import java.util.*

@Transactional
@Service
class UserService(
    private val userRepository: UserRepository,
    private val verificationTokenRepository: VerificationTokenRepository,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: MailService
) {

    fun registerUser(user: User) {
        if (userRepository.findByEmail(user.email) != null) {
            throw EmailAlreadyInUseException()
        }

        userRepository.save(user)

        val token = createVerificationToken(user)
        verificationTokenRepository.save(token)

        emailService.sendVerificationMail(user.email, createVerificationLink(token.token))
    }

    fun verifyUser(token: String): Boolean {
        val verifToken = verificationTokenRepository.findByToken(token)

        if (verifToken != null) {
            verifToken.user.isVerified = true
            verificationTokenRepository.delete(verifToken)

            return true
        }

        return false
    }

    private fun createVerificationToken(user: User): VerificationToken {
        val rawToken = UUID.randomUUID().toString()
        val token = passwordEncoder.encode(rawToken)

        val verificationToken = VerificationToken(
            token = token,
            expiryDate = LocalDateTime.now().plusDays(1),
            user = user
        )

        return verificationToken
    }

    private fun createVerificationLink(tokenValue: String): String {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/users/verify")
            .queryParam("token", tokenValue)
            .build()
            .toUriString()
    }
}