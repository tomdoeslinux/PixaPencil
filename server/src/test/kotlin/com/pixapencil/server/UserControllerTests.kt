package com.pixapencil.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.pixapencil.server.dtos.GetUserDTO
import com.pixapencil.server.dtos.LoginUserDTO
import com.pixapencil.server.dtos.RegisterUserDTO
import com.pixapencil.server.services.UserService
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UserControllerTests {

    @MockkBean
    lateinit var userService: UserService

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

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

        every { userService.registerUser(registerUser) } returns GetUserDTO(
            id = expId,
            username = registerUser.username,
            email = registerUser.email,
            profilePictureUrl = pfpUrl
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

        verify { userService.registerUser(registerUser) }
    }

    @Test
    fun `user can login without errors`() {
        val loginUserDTO = LoginUserDTO(email = "user@gmail.com", password = "password")

        val expectedGetUser = GetUserDTO(
            username = "user",
            email = "user@example.com",
            profilePictureUrl = "https://example.com/profile/dummyUser.jpg"
        )

        every { userService.authenticateUser(loginUserDTO) } returns expectedGetUser

        val jsonLoginUser = mapper.writeValueAsString(loginUserDTO)

        mockMvc.post("/api/users/login") {
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
        val userId = 1L
        val verificationCode = "123456"

        every { userService.verifyUser(userId, verificationCode) } returns true

        mockMvc.post("/api/users/verify") {
            param("userId", userId.toString())
            param("code", verificationCode)
        }.andExpect {
            status { isOk() }
        }
    }
}