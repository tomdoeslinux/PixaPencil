package com.pixapencil.server.dtos

import com.pixapencil.server.domain.User

data class GetAuthorDTO(val username: String, val profilePictureUrl: String)

fun User.toGetAuthorDTO(): GetAuthorDTO {
    return GetAuthorDTO(
        username = this.username,
        profilePictureUrl = this.profilePictureUrl
    )
}