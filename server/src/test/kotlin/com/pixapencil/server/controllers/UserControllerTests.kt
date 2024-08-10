package com.pixapencil.server.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.pixapencil.server.domain.User
import com.pixapencil.server.domain.VerificationToken
import com.pixapencil.server.dtos.GetUserDTO
import com.pixapencil.server.dtos.LoginUserDTO
import com.pixapencil.server.dtos.RegisterUserDTO
import com.pixapencil.server.repos.UserRepository
import com.pixapencil.server.services.AuthUser
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class UserControllerTests {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Test
    fun `user can register without errors`() {
        val pfpUrl = "https://example.com/profile/dummyUser.jpg"
        val expId = 1L

        val registerUser = RegisterUserDTO(
            username = "user",
            email = "user@gmail.com",
            password = "password",
            confirmPassword = "password",
        )

        val jsonRegisterUser = mapper.writeValueAsString(registerUser)

        mockMvc.post("/api/users/register") {
            contentType = MediaType.APPLICATION_JSON
            content = jsonRegisterUser
        }.andExpect {
            status { isOk() }
            jsonPath("$.id").value(expId)
            jsonPath("$.username").value(registerUser.username)
            jsonPath("$.email").value(registerUser.email)
            jsonPath("$.profilePictureUrl").value(pfpUrl)
        }
    }

    @Test
    fun `user can login without errors`() {
        // Setup
        val user = User(
            id = 1L,
            username = "user",
            email = "user@example.com",
            password = passwordEncoder.encode("password"),
            profilePictureUrl = "https://example.com/profile/dummyUser.jpg",
            isVerified = true
        )
        userRepository.save(user)

        // Test
        val loginUserDTO = LoginUserDTO(email = "user@gmail.com", password = "password")

        val expectedGetUser = GetUserDTO(
            username = "user",
            email = "user@example.com",
            profilePictureUrl = "https://example.com/profile/dummyUser.jpg"
        )

        val jsonLoginUser = mapper.writeValueAsString(loginUserDTO)

        mockMvc.post("/api/users/login") {
            with(user(AuthUser(user)))
            contentType = MediaType.APPLICATION_JSON
            content = jsonLoginUser
        }.andExpect {
            status { isOk() }
            jsonPath("$.username").value(expectedGetUser.username)
            jsonPath("$.email").value(expectedGetUser.email)
            jsonPath("$.profilePictureUrl").value(expectedGetUser.profilePictureUrl)
        }
    }

    @Test
    fun `user can be verified`() {
        // Setup
        val user = User(
            id = 1L,
            username = "user",
            email = "user@example.com",
            password = passwordEncoder.encode("password"),
            profilePictureUrl = "https://example.com/profile/dummyUser.jpg",
            isVerified = false,
        )
        userRepository.save(user)

        val verificationToken = VerificationToken(
            user = user,
            expiryDate = LocalDateTime.now().plusDays(1),
            code = "123456"
        )

        // Test
        mockMvc.post("/api/users/verify") {
            param("userId", user.id.toString())
            param("code", verificationToken.code)
        }.andExpect {
            status { isOk() }
        }
    }
}