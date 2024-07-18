package com.pixapencil.server.dtos

import com.pixapencil.server.config.TimeZoneFilter.Companion.TIME_ZONE_REQUEST_ATTRIBUTE
import com.pixapencil.server.domain.Creation
import com.pixapencil.server.domain.User
import com.pixapencil.server.formatTimeSinceUTC
import com.pixapencil.server.formatUploadDateTZ
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import java.time.ZoneId

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

fun Creation.toGetCreationDTO(
    context: User,
    timeZone: ZoneId = ZoneId.of(
        RequestContextHolder.currentRequestAttributes()
            .getAttribute(TIME_ZONE_REQUEST_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST) as String? ?: "UTC"
    )
): GetCreationDTO {
    return GetCreationDTO(
        id = this.id!!,
        title = this.title,
        description = this.description,
        imageUrl = "https://pixapencil-gallery.s3.ap-southeast-2.amazonaws.com/" + this.imageKey,
        likeCount = this.likeCount,
        isLiked = this.likedBy.contains(context),
        uploadDate = formatUploadDateTZ(this.createdAt, timeZone),
        author = this.user.toGetAuthorDTO(),
        timeSince = formatTimeSinceUTC(this.createdAt)
    )
}
