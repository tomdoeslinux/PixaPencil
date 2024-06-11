package com.pixapencil.server.dtos

import com.pixapencil.server.domain.Comment
import com.pixapencil.server.formatTimeSince
import com.pixapencil.server.formatUploadDate

data class GetCommentDTO(
    val id: Long,
    val text: String,
    val author: GetAuthorDTO,
    val uploadDate: String,
    val timeSince: String,
)

fun Comment.toGetCommentDTO(): GetCommentDTO {
    return GetCommentDTO(
        id = this.id!!,
        text = this.text,
        author = this.user.toGetAuthorDTO(),
        uploadDate = formatUploadDate(this.createdAt!!),
        timeSince = formatTimeSince(this.createdAt!!)
    )
}
