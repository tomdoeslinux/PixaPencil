package com.pixapencil.server.services

import com.pixapencil.server.domain.Comment
import com.pixapencil.server.dtos.AddCommentDTO
import com.pixapencil.server.dtos.EditCommentDTO
import com.pixapencil.server.repos.CommentRepository
import com.pixapencil.server.repos.CreationRepository
import com.pixapencil.server.repos.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val userRepository: UserRepository,
    private val creationRepository: CreationRepository,
    private val commentRepository: CommentRepository
) {

    fun addComment(addCommentDTO: AddCommentDTO) {
        val user = userRepository.findByIdOrNull(addCommentDTO.userId) ?: throw EntityNotFoundException()
        val creation = creationRepository.findByIdOrNull(addCommentDTO.creationId) ?: throw EntityNotFoundException()

        val comment = Comment(
            text = addCommentDTO.text,
            user = user,
            creation = creation
        )
        commentRepository.save(comment)
    }

    fun deleteComment(id: Long) {
        commentRepository.deleteById(id)
    }

    fun editComment(id: Long, editComment: EditCommentDTO) {
        val comment = commentRepository.findByIdOrNull(id) ?: throw EntityNotFoundException()
        comment.text = editComment.text

        commentRepository.save(comment)
    }
}
