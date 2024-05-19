package com.pixapencil.server.dtos

data class AddCreationDTO(
    val title: String,
    val description: String,
    val coverImageUrl: String,
)