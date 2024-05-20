package com.pixapencil.server.controllers

import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.UploadCreationDTO
import com.pixapencil.server.services.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun registerUser(@RequestBody user: User) = userService.registerUser(user)

    @GetMapping("/verify")
    fun verifyUser(@RequestParam userId: Long, @RequestParam token: String) = userService.verifyUser(userId, token)

    @GetMapping("/creations/get-upload-url")
    fun getCreationUploadUrl(@RequestParam mimeType: String) = userService.getCreationUploadUrl(mimeType)

    @PostMapping("/{id}/creations")
    fun uploadCreation(@PathVariable id: Long, addCreation: UploadCreationDTO) = userService.uploadCreation(addCreation, id)
}