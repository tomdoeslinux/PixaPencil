package com.pixapencil.server.controllers

import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.AddCommentDTO
import com.pixapencil.server.dtos.EditCommentDTO
import com.pixapencil.server.services.CommentService
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comments")
class CommentController(
    private val commentService: CommentService,
) {
    @GetMapping
    fun getComments(
        @RequestParam creationId: Long,
        @RequestParam page: Int = 0,
    ) = commentService.getComments(creationId, PageRequest.of(page, 30))

    @PostMapping
    fun addComment(
        @RequestBody addCommentDTO: AddCommentDTO,
        @AuthenticationPrincipal(expression = "user") user: User,
    ) = commentService.addComment(addCommentDTO, user)

    @DeleteMapping("/{id}")
    fun deleteComment(
        @PathVariable id: Long,
    ) = commentService.deleteComment(id)

    @PutMapping("/{id}")
    fun editComment(
        @PathVariable id: Long,
        @RequestBody editCommentDTO: EditCommentDTO,
    ) = commentService.editComment(id, editCommentDTO)
}
