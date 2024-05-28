package com.pixapencil.server.services

import com.pixapencil.server.domain.Comment
import com.pixapencil.server.dtos.GetCommentDTO
import com.pixapencil.server.dtos.GetCreationDTO
import com.pixapencil.server.dtos.toGetCommentDTO
import com.pixapencil.server.dtos.toGetCreationDTO
import com.pixapencil.server.repos.CommentRepository
import com.pixapencil.server.repos.CreationRepository
import com.pixapencil.server.repos.UserRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Transactional
@Service
class CreationService(
    private val creationRepository: CreationRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository,
) {

    fun getCreations(): List<GetCreationDTO> {
        val user = userRepository.findByIdOrNull(1) ?: throw EntityNotFoundException()

        return creationRepository.findAll().map { it.toGetCreationDTO(user) }
    }

    fun likeCreation(creationId: Long, userId: Long) {
        val user = userRepository.findByIdOrNull(userId) ?: throw EntityNotFoundException()
        val creation = creationRepository.findByIdOrNull(creationId) ?: throw EntityNotFoundException()

        if (creation.likedBy.contains(user)) {
            throw IllegalArgumentException("User has already liked this creation")
        }

        creation.likedBy.add(user)
        user.likedCreations.add(creation)
        ++creation.likeCount
        creationRepository.save(creation)
    }

    fun unlikeCreation(creationId: Long, userId: Long) {
        val user = userRepository.findByIdOrNull(userId) ?: throw EntityNotFoundException()
        val creation = creationRepository.findByIdOrNull(creationId) ?: throw EntityNotFoundException()

        if (!creation.likedBy.contains(user)) {
            throw IllegalArgumentException("User has not liked this creation")
        }

        creation.likedBy.remove(user)
        user.likedCreations.remove(creation)
        --creation.likeCount
        creationRepository.save(creation)
    }

    fun addComment(creationId: Long, userId: Long, comment: Comment) {
        val user = userRepository.findByIdOrNull(userId) ?: throw EntityNotFoundException()
        val creation = creationRepository.findByIdOrNull(creationId) ?: throw EntityNotFoundException()

        creation.comments.add(comment)
        comment.user = user
        creationRepository.save(creation)
    }

    fun deleteComment(commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw EntityNotFoundException()
        commentRepository.delete(comment)
    }

    fun getComments(creationId: Long): List<GetCommentDTO> {
        val creation = creationRepository.findByIdOrNull(creationId) ?: throw EntityNotFoundException()

        return creation.comments.map { it.toGetCommentDTO() }
    }
}