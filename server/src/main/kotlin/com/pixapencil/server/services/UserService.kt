package com.pixapencil.server.services

import com.pixapencil.server.domain.User
import com.pixapencil.server.domain.VerificationToken
import com.pixapencil.server.dtos.LoginUserDTO
import com.pixapencil.server.dtos.RegisterUserDTO
import com.pixapencil.server.dtos.GetUserDTO
import com.pixapencil.server.dtos.toGetUserDTO
import com.pixapencil.server.exceptions.EmailAlreadyInUseException
import com.pixapencil.server.repos.UserRepository
import com.pixapencil.server.repos.VerificationTokenRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class UserService(
    private val userRepository: UserRepository,
    private val verificationTokenRepository: VerificationTokenRepository,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: MailService,
) {
    fun registerUser(registerUser: RegisterUserDTO): GetUserDTO {
        if (userRepository.findByEmail(registerUser.email) != null) {
            throw EmailAlreadyInUseException()
        }

        val user = User(
            username = registerUser.username,
            email = registerUser.email,
            password = passwordEncoder.encode(registerUser.password),
            profilePictureUrl = "https://example.com/profile/dummyUser.jpg",
        )
        userRepository.save(user)

        val (token, rawToken) = createVerificationToken(user)
        verificationTokenRepository.save(token)

        emailService.sendVerificationMail(user.email, rawToken)

        // We want to expose id since it's needed when verifying account
        return user.toGetUserDTO(exposeId = true)
    }

    fun verifyUser(
        userId: Long,
        verificationCode: String,
    ): Boolean {
        val user = userRepository.findByIdOrNull(userId) ?: throw EntityNotFoundException()
        val verifToken = verificationTokenRepository.findByUser(user)

        if (verifToken?.code == verificationCode) {
            user.isVerified = true

            userRepository.save(user)
            verificationTokenRepository.delete(verifToken)

            return true
        }

        return false
    }

    fun authenticateUser(loginUser: LoginUserDTO): GetUserDTO? {
        val user = userRepository.findByEmail(loginUser.email) ?: return null

        return if (passwordEncoder.matches(loginUser.password, user.password)) {
           user.toGetUserDTO()
        } else {
           null
        }
    }

    private fun createVerificationToken(user: User): Pair<VerificationToken, String> {
        val rawToken = (100000..999999).random().toString()

        val verificationToken =
            VerificationToken(
                code = rawToken,
                expiryDate = LocalDateTime.now().plusDays(1),
                user = user,
            )

        return Pair(verificationToken, rawToken)
    }
}
