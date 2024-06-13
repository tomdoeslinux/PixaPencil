package com.pixapencil.server.services

import com.pixapencil.server.domain.Creation
import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.*
import com.pixapencil.server.repos.CreationRepository
import com.pixapencil.server.repos.UserRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Transactional
@Service
class CreationService(
    private val creationRepository: CreationRepository,
    private val userRepository: UserRepository,
    private val s3Service: S3Service,
) {
    fun getCreations(pageable: Pageable): Page<GetCreationDTO> {
        val user = userRepository.findByIdOrNull(1) ?: throw EntityNotFoundException()

        return creationRepository.findAll(pageable).map { it.toGetCreationDTO(user) }
    }

    fun getCreation(
        id: Long,
        user: User,
    ): GetCreationDTO {
        val creation = creationRepository.findByIdOrNull(id) ?: throw EntityNotFoundException()

        return creation.toGetCreationDTO(user)
    }

    fun likeCreation(
        creationId: Long,
        user: User,
    ) {
        val creation = creationRepository.findByIdOrNull(creationId) ?: throw EntityNotFoundException()

        if (creation.likedBy.contains(user)) {
            throw IllegalArgumentException("User has already liked this creation")
        }

        creation.likedBy.add(user)
        user.likedCreations.add(creation)
        ++creation.likeCount
        creationRepository.save(creation)
    }

    fun deleteCreation(id: Long) {
        val toDelete = creationRepository.findByIdOrNull(id) ?: throw EntityNotFoundException()

        creationRepository.delete(toDelete)
        s3Service.deleteObject(toDelete.imageKey)
    }

    fun unlikeCreation(
        creationId: Long,
        user: User,
    ) {
        val creation = creationRepository.findByIdOrNull(creationId) ?: throw EntityNotFoundException()

        if (!creation.likedBy.contains(user)) {
            throw IllegalArgumentException("User has not liked this creation")
        }

        creation.likedBy.remove(user)
        user.likedCreations.remove(creation)
        --creation.likeCount
        creationRepository.save(creation)
    }

    fun getCreationUploadUrl(mimeType: String): Map<String, String> {
        val key = s3Service.generateRandomKey(mimeType)
        val uploadUrl = s3Service.createSignedPutURL(key)

        return mapOf("url" to uploadUrl, "key" to key)
    }

    fun uploadCreation(uploadCreation: UploadCreationDTO, user: User) {
        val creation =
            Creation(
                title = uploadCreation.title,
                description = uploadCreation.description,
                imageKey = uploadCreation.imageKey,
                user = user,
            )

        creationRepository.save(creation)
    }
}
