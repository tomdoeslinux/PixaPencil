package com.pixapencil.server.services

import com.pixapencil.server.domain.Creation
import com.pixapencil.server.domain.User
import com.pixapencil.server.domain.VerificationToken
import com.pixapencil.server.dtos.AddCreationDTO
import com.pixapencil.server.exceptions.EmailAlreadyInUseException
import com.pixapencil.server.repos.UserRepository
import com.pixapencil.server.repos.VerificationTokenRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
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
    private val emailService: MailService,
    private val s3Service: S3Service,
) {

    fun registerUser(user: User) {
        if (userRepository.findByEmail(user.email) != null) {
            throw EmailAlreadyInUseException()
        }

        userRepository.save(user)

        val (token, rawToken) = createVerificationToken(user)
        verificationTokenRepository.save(token)

        emailService.sendVerificationMail(user.email, createVerificationLink(user, rawToken))
    }

    fun verifyUser(userId: Long, token: String): Boolean {
        val user = userRepository.findByIdOrNull(userId) ?: throw EntityNotFoundException()
        val verifToken = verificationTokenRepository.findByUser(user)

        if (verifToken != null) {
            user.isVerified = true

            userRepository.save(user)
            verificationTokenRepository.delete(verifToken)

            return true
        }

        return false
    }

    fun getCreationUploadUrl(mimeType: String) = s3Service.createSignedPutURL(mimeType)

    fun addCreation(addCreation: AddCreationDTO, userId: Long) {
        val user = userRepository.findByIdOrNull(userId) ?: throw EntityNotFoundException()

        val creation = Creation(
            title = addCreation.title,
            description = addCreation.description,
            coverImageUrl = addCreation.coverImageUrl,
            user = user,
        )

        user.creations.add(creation)
        userRepository.save(user)
    }

    private fun createVerificationToken(user: User): Pair<VerificationToken, String> {
        val rawToken = UUID.randomUUID().toString()
        val token = passwordEncoder.encode(rawToken)

        val verificationToken = VerificationToken(
            token = token,
            expiryDate = LocalDateTime.now().plusDays(1),
            user = user
        )

        return Pair(verificationToken, rawToken)
    }

    private fun createVerificationLink(user: User, tokenValue: String): String {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/users/verify")
            .queryParam("token", tokenValue)
            .queryParam("userId", user.id)
            .build()
            .toUriString()
    }
}