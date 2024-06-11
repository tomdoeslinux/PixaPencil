package com.pixapencil.server.dtos

import com.pixapencil.server.domain.User

data class GetAuthorDTO(val username: String, val profilePictureUrl: String)

fun User.toGetAuthorDTO(): GetAuthorDTO {
    return GetAuthorDTO(
        username = this.username,
        profilePictureUrl = "https://pixapencil-gallery.s3.ap-southeast-2.amazonaws.com/" + this.profilePictureUrl,
    )
}
