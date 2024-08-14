package com.pixapencil.server.dtos

data class RegisterUserDTO(
    val username: String,
    val email: String,
    val password: String,
)