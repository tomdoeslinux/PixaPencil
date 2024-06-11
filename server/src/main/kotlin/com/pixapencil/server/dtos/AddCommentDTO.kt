package com.pixapencil.server.dtos

data class AddCommentDTO(
    val text: String,
    val creationId: Long,
)
