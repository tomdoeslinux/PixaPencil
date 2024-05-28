package com.pixapencil.server.controllers

import com.pixapencil.server.services.CreationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import com.pixapencil.server.domain.Comment
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
@RequestMapping("/api/creations")
class CreationController(private val creationService: CreationService) {

    @GetMapping
    fun getCreations() = creationService.getCreations()

    @PostMapping("/{id}/like")
    fun likeCreation(@PathVariable id: Long, @RequestParam userId: Long) = creationService.likeCreation(id, userId)

    @PostMapping("/{id}/unlike")
    fun unlikeCreation(@PathVariable id: Long, @RequestParam userId: Long) = creationService.unlikeCreation(id, userId)

    @GetMapping("/{id}/comments")
    fun getComments(@PathVariable id: Long) = creationService.getComments(id)
}