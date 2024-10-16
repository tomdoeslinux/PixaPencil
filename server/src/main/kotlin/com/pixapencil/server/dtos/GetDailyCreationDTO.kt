package com.pixapencil.server.dtos

import com.pixapencil.server.domain.DailyCreation

data class GetDailyCreationDTO(
    val imageUrl: String,
    val author: GetAuthorDTO,
)

fun DailyCreation.toGetDailyCreationDTO(): GetDailyCreationDTO {
    return GetDailyCreationDTO(
        imageUrl = "https://pixapencil-gallery.s3.ap-southeast-2.amazonaws.com/" + this.creation.imageKey,
        author = this.creation.user.toGetAuthorDTO()
    )
}