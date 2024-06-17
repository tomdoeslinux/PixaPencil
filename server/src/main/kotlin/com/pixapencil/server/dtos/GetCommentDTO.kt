package com.pixapencil.server.dtos

import com.pixapencil.server.config.TimeZoneFilter.Companion.TIME_ZONE_REQUEST_ATTRIBUTE
import com.pixapencil.server.domain.Comment
import com.pixapencil.server.formatTimeSinceUTC
import com.pixapencil.server.formatUploadDateTZ
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import java.time.ZoneId

data class GetCommentDTO(
    val id: Long,
    val text: String,
    val author: GetAuthorDTO,
    val uploadDate: String,
    val timeSince: String,
)

fun Comment.toGetCommentDTO(
    timeZone: ZoneId = ZoneId.of(RequestContextHolder.currentRequestAttributes()
        .getAttribute(TIME_ZONE_REQUEST_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST) as String)
): GetCommentDTO {
    return GetCommentDTO(
        id = this.id!!,
        text = this.text,
        author = this.user.toGetAuthorDTO(),
        uploadDate = formatUploadDateTZ(this.createdAt!!, timeZone),
        timeSince = formatTimeSinceUTC(this.createdAt!!)
    )
}
