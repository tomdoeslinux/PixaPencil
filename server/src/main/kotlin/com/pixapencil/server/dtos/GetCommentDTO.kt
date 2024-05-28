package com.pixapencil.server.dtos

import com.pixapencil.server.domain.Comment

data class GetCommentDTO(
    val id: Long,
    val text: String,
    val author: GetAuthorDTO
)

fun Comment.toGetCommentDTO(): GetCommentDTO {
    return GetCommentDTO(
        id = this.id!!,
        text = this.text,
        author = this.user.toGetAuthorDTO()
    )
}