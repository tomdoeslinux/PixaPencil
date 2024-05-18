package com.pixapencil.server.controllers

import com.pixapencil.server.dtos.GetCreationDTO
import com.pixapencil.server.dtos.toGetCreationDTO
import com.pixapencil.server.repos.CreationRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/creations")
class CreationController(private val creationRepository: CreationRepository) {
    
    @GetMapping
    fun getCreations(): List<GetCreationDTO> = creationRepository.findAll().map { it.toGetCreationDTO() }
}