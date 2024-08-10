package com.pixapencil.server.controllers

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
        @AuthenticationPrincipal authUser: AuthUser,
    ) = creationService.getCreation(id, authUser)

    @PostMapping("/{id}/like")
    fun likeCreation(
        @PathVariable id: Long,
        @AuthenticationPrincipal authUser: AuthUser,
    ) = creationService.likeCreation(id, authUser)

    @PostMapping("/{id}/unlike")
    fun unlikeCreation(
        @PathVariable id: Long,
        @AuthenticationPrincipal authUser: AuthUser,
    ) = creationService.unlikeCreation(id, authUser)

    @DeleteMapping("/{id}")
    fun deleteCreation(
        @PathVariable id: Long,
    ) = creationService.deleteCreation(id)

    @GetMapping("/get-upload-url")
    fun getCreationUploadUrl(
        @RequestParam mimeType: String,
    ) = creationService.getCreationUploadUrl(mimeType)

    @PostMapping("/upload")
    fun uploadCreation(
        @AuthenticationPrincipal authUser: AuthUser,
        @RequestBody uploadCreation: UploadCreationDTO,
    ) = creationService.uploadCreation(uploadCreation, authUser)
}
