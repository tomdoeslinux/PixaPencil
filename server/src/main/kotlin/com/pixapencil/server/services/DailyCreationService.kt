package com.pixapencil.server.services

import com.pixapencil.server.domain.Creation
import com.pixapencil.server.dtos.GetCreationDTO
import com.pixapencil.server.dtos.GetDailyCreationDTO
import com.pixapencil.server.dtos.toGetCreationDTO
import com.pixapencil.server.dtos.toGetDailyCreationDTO
import com.pixapencil.server.repos.DailyCreationRepository
import com.pixapencil.server.repos.UserRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDate

@Transactional
@Service
class DailyCreationService(
    private val dailyCreationRepository: DailyCreationRepository,
    private val userRepository: UserRepository,
) {

    @EventListener(ApplicationReadyEvent::class)
    fun initializeDailyCreation() {
        updateDailyCreationIfNeeded()
    }

    @Scheduled(cron = "0 0 0 * * *")
    fun updateDailyCreation() {
        updateDailyCreationIfNeeded()
    }

    fun getDailyCreation(): GetDailyCreationDTO {
        val dailyCreation = dailyCreationRepository.getDailyCreation() ?: throw EntityNotFoundException()

        return dailyCreation.toGetDailyCreationDTO()
    }

    private fun updateDailyCreationIfNeeded() {
        val today = LocalDate.now()
        val existing = dailyCreationRepository.findByDate(today)

        if (existing == null) {
            dailyCreationRepository.updateDailyCreation()
        }
    }
}