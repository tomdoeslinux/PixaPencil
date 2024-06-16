package com.pixapencil.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.RegisterUserDTO
import com.pixapencil.server.services.CreationService
import com.pixapencil.server.services.UserService
import io.mockk.every
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

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

    fun `user can register without errors`() {
        val registerUser = RegisterUserDTO(
            username = "user",
            email = "user@gmail.com",
            password = "password",
            confirmPassword = "password",
        )

        mockMvc.post("/api/users/register") {
            content = registerUser
        }.andExpect {
            status { isOk() }
        }

        verify { userService.registerUser(registerUser) }
    }

   // add unit tests here for verifyUser
}