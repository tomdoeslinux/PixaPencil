package com.pixapencil.server.services

import com.ninjasquad.springmockk.MockkBean
import com.pixapencil.server.authContext
import com.pixapencil.server.domain.User
import com.pixapencil.server.domain.VerificationToken
import com.pixapencil.server.dtos.RegisterUserDTO
import com.pixapencil.server.dtos.toGetUserDTO
import com.pixapencil.server.exceptions.EmailAlreadyInUseException
import com.pixapencil.server.repos.UserRepository
import com.pixapencil.server.repos.VerificationTokenRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTests {

    @MockkBean
    lateinit var userRepository: UserRepository

    @MockkBean
    lateinit var verificationTokenRepository: VerificationTokenRepository

    @MockkBean
    lateinit var passwordEncoder: PasswordEncoder

    @MockkBean
    lateinit var emailService: MailService

    @Autowired
    lateinit var userService: UserService

    val registerUser = RegisterUserDTO(
        username = "dummyUser",
        email = "dummy@example.com",
        password = "dummyPassword",
    )

    val verificationToken = VerificationToken(
        code = "123456",
        expiryDate = LocalDateTime.now().plusDays(1),
        user = authContext.user
    )

    val unverifiedUser = User(
        id = 1L,
        username = "user",
        email = "user@gmail.com",
        password = "password",
        profilePictureUrl = "https://example.com/profile/dummyUser.jpg",
        isVerified = false
    )

    @Test
    fun `registerUser should register a user successfully`() {
        // User with the same email shouldn't exist for the scenario (email should be unique)
        every { userRepository.findByEmail(registerUser.email) } returns null
        every { userRepository.save(any()) } answers { firstArg() }
        every { passwordEncoder.encode(registerUser.password) } returns "encodedPassword"
        every { verificationTokenRepository.save(any()) } answers { firstArg() }
        justRun { emailService.sendVerificationMail(registerUser.email, any()) }

        val result = userService.registerUser(registerUser)

        assertEquals(
            User(
                username = registerUser.username,
                email = registerUser.email,
                password = registerUser.password,
                profilePictureUrl = "https://example.com/profile/dummyUser.jpg"
            ).toGetUserDTO(exposeId = true),
            result
        )

        verify { userRepository.findByEmail(registerUser.email) }
        verify { userRepository.save(any()) }
        verify { verificationTokenRepository.save(any()) }
        verify { emailService.sendVerificationMail(registerUser.email, any()) }
        verify { passwordEncoder.encode(registerUser.password) }
    }

    @Test
    fun `registerUser should throw EmailAlreadyInUseException when email is already in use`() {
        every { userRepository.findByEmail(registerUser.email) } returns authContext.user

        assertThrows<EmailAlreadyInUseException> {
            userService.registerUser(registerUser)
        }

        verify { userRepository.findByEmail(registerUser.email) }
    }

    @Test
    fun `verifyUser should verify user successfully`() {
        every { userRepository.findByIdOrNull(1L) } returns unverifiedUser
        every { verificationTokenRepository.findByUser(unverifiedUser) } returns verificationToken
        every { userRepository.save(any()) } answers { firstArg() }
        justRun { verificationTokenRepository.delete(verificationToken) }

        val result = userService.verifyUser(1L, "123456")

        assertTrue(result)
        assertTrue(unverifiedUser.isVerified)

        verify { userRepository.findByIdOrNull(unverifiedUser.id) }
        verify { verificationTokenRepository.findByUser(unverifiedUser) }
        verify { userRepository.save(unverifiedUser) }
        verify { verificationTokenRepository.delete(verificationToken) }
    }

    @Test
    fun `verifyUser should return false for incorrect verification code`() {
        every { userRepository.findByIdOrNull(1L) } returns unverifiedUser
        every { verificationTokenRepository.findByUser(unverifiedUser) } returns verificationToken

        val result = userService.verifyUser(1L, "wrongCode")

        assertFalse(result)
        assertFalse(unverifiedUser.isVerified)

        verify { userRepository.findByIdOrNull(1L) }
        verify { verificationTokenRepository.findByUser(unverifiedUser) }
    }
}
