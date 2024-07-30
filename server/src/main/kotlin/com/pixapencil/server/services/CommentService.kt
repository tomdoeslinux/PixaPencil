package com.pixapencil.server.services

import com.pixapencil.server.domain.Comment
import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.AddCommentDTO
import com.pixapencil.server.dtos.EditCommentDTO
import com.pixapencil.server.dtos.GetCommentDTO
import com.pixapencil.server.dtos.toGetCommentDTO
import com.pixapencil.server.repos.CommentRepository
import com.pixapencil.server.repos.CreationRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Transactional
@Service
class CommentService(
    private val creationRepository: CreationRepository,
    private val commentRepository: CommentRepository,
) {
    fun getComments(
        creationId: Long,
        pageable: Pageable,
    ): Page<GetCommentDTO> {
        return commentRepository.getCommentsByCreationId(creationId, pageable).map {
            it.toGetCommentDTO()
        }
    }

    fun addComment(
        addCommentDTO: AddCommentDTO,
        user: User,
    ) {
        val creation = creationRepository.findByIdOrNull(addCommentDTO.creationId) ?: throw EntityNotFoundException()

        val comment =
            Comment(
                text = addCommentDTO.text,
                user = user,
                creation = creation,
            )
        ++comment.creation.commentCount
        commentRepository.save(comment)
    }

    fun deleteComment(id: Long) {
        val comment = commentRepository.findByIdOrNull(id) ?: throw EntityNotFoundException()
        --comment.creation.commentCount
        commentRepository.delete(comment)
    }

    fun editComment(
        id: Long,
        editComment: EditCommentDTO,
    ) {
        val comment = commentRepository.findByIdOrNull(id) ?: throw EntityNotFoundException()
        comment.text = editComment.text

        commentRepository.save(comment)
    }
}
