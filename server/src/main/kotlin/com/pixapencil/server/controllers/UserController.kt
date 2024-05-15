package com.pixapencil.server.controllers

import com.pixapencil.server.domain.User
import com.pixapencil.server.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun registerUser(@RequestBody user: User) = userService.registerUser(user)

    @PostMapping("/verify")
    fun verifyUser(@RequestParam token: String) = userService.verifyUser(token)
}