package com.pixapencil.server.controllers

import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.LoginUserDTO
import com.pixapencil.server.dtos.RegisterUserDTO
import com.pixapencil.server.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun registerUser(
        @RequestBody registerUser: RegisterUserDTO,
    ) = userService.registerUser(registerUser)

    @PostMapping("/verify")
    fun verifyUser(
        @RequestParam userId: Long,
        @RequestParam code: String,
    ) = userService.verifyUser(userId, code)

    @PostMapping("/login")
    fun loginUser(@RequestBody loginUser: LoginUserDTO) =
        userService.authenticateUser(loginUser)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
}
