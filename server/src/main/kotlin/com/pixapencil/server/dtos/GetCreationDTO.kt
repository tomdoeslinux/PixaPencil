package com.pixapencil.server.dtos

import com.pixapencil.server.domain.Creation
import com.pixapencil.server.domain.User

data class GetCreationDTO(
    val id: Long,
    val title: String,
    val description: String,
    val imageUrl: String,
    val likeCount: Int,
    val isLiked: Boolean,
    val author: GetAuthorDTO,
    val uploadDate: String,
    val timeSince: String,
)

fun Creation.toGetCreationDTO(context: User): GetCreationDTO {
    return GetCreationDTO(
        id = this.id!!,
        title = this.title,
        description = this.description,
        imageUrl = "https://pixapencil-gallery.s3.ap-southeast-2.amazonaws.com/" + this.imageKey,
        likeCount = this.likeCount,
        isLiked = this.likedBy.contains(context),
        uploadDate = this.createdAt.toString(),
        author = this.user.toGetAuthorDTO(),
        timeSince = this.createdAt.toString(),
    )
}
