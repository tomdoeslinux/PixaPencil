package com.pixapencil.server.repos

import com.pixapencil.server.domain.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun getCommentsByCreationId(
        creationId: Long,
        page: Pageable,
    ): Page<Comment>
}
