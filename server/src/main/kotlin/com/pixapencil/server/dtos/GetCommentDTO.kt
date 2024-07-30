package com.pixapencil.server.dtos

import com.pixapencil.server.domain.Comment
import java.time.LocalDate
import java.time.LocalDateTime

data class GetCommentDTO(
    val id: Long,
    val text: String,
    val author: GetAuthorDTO,
    val uploadDate: LocalDateTime,
)

fun Comment.toGetCommentDTO(): GetCommentDTO {
    return GetCommentDTO(
        id = this.id!!,
        text = this.text,
        author = this.user.toGetAuthorDTO(),
        uploadDate = this.createdAt,
    )
}
