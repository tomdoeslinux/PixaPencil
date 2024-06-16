package com.pixapencil.server.controllers

import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.RegisterUserDTO
import com.pixapencil.server.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun registerUser(
        @RequestBody registerUser: RegisterUserDTO,
    ) = userService.registerUser(registerUser)

    @GetMapping("/verify")
    fun verifyUser(
        @RequestParam userId: Long,
        @RequestParam token: String,
    ) = userService.verifyUser(userId, token)
}
