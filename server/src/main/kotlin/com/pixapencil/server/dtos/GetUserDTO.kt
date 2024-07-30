package com.pixapencil.server.dtos

import com.pixapencil.server.domain.User

data class GetUserDTO(
    val id: Long? = null,
    val username: String,
    val email: String,
    val profilePictureUrl: String
)

fun User.toGetUserDTO(exposeId: Boolean = false): GetUserDTO {
    return GetUserDTO(
        id = if (exposeId) this.id else null,
        username = this.username,
        email = this.email,
        profilePictureUrl = this.profilePictureUrl,
    )
}