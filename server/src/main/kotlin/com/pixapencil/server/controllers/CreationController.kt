package com.pixapencil.server.controllers

import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.UploadCreationDTO
import com.pixapencil.server.services.AuthUser
import com.pixapencil.server.services.CreationService
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/creations")
class CreationController(private val creationService: CreationService) {

    @GetMapping("/gallery")
    fun getCreations(
        @RequestParam page: Int = 0,
    ) = creationService.getCreations(PageRequest.of(page, 30))

    @GetMapping("/{id}")
    fun getCreation(
        @PathVariable id: Long,
        @AuthenticationPrincipal(expression = "user") user: User,
    ) = creationService.getCreation(id, user)

    @PostMapping("/{id}/like")
    fun likeCreation(
        @PathVariable id: Long,
        @AuthenticationPrincipal user: User,
    ) = creationService.likeCreation(id, user)

    @PostMapping("/{id}/unlike")
    fun unlikeCreation(
        @PathVariable id: Long,
        @AuthenticationPrincipal user: User,
    ) = creationService.unlikeCreation(id, user)

    @DeleteMapping("/{id}")
    fun deleteCreation(
        @PathVariable id: Long,
    ) = creationService.deleteCreation(id)

    @GetMapping("/creations/get-upload-url")
    fun getCreationUploadUrl(
        @RequestParam mimeType: String,
    ) = creationService.getCreationUploadUrl(mimeType)

    @PostMapping("/creations/upload")
    fun uploadCreation(
        @RequestParam userId: Long,
        @RequestBody uploadCreation: UploadCreationDTO,
    ) = creationService.uploadCreation(uploadCreation, userId)
}
