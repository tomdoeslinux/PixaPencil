package com.pixapencil.server.repos

import com.pixapencil.server.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>