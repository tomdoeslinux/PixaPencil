package com.pixapencil.server.controllers

import com.pixapencil.server.dtos.AddCommentDTO
import com.pixapencil.server.dtos.EditCommentDTO
import com.pixapencil.server.services.CommentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comments")
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun addComment(@RequestBody addCommentDTO: AddCommentDTO) = commentService.addComment(addCommentDTO)

    @DeleteMapping("/{id}")
    fun deleteComment(@PathVariable id: Long) = commentService.deleteComment(id)
}