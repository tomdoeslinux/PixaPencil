package com.pixapencil.server.repos

import com.pixapencil.server.domain.DailyCreation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate
import java.util.*

interface DailyCreationRepository : JpaRepository<DailyCreation, Long> {
    @Modifying
    @Query(value = """
        INSERT INTO daily_creations (creation_id, date)
        SELECT c.id, CURRENT_DATE
        FROM creations c
        WHERE c.id NOT IN (SELECT dc.creation_id FROM daily_creations dc)
        ORDER BY RAND() LIMIT 1
    """, nativeQuery = true)
    fun updateDailyCreation()

    @Query(
        value = "SELECT * FROM daily_creations ORDER BY date DESC LIMIT 1",
        nativeQuery = true
    )
    fun getDailyCreation(): DailyCreation?

    fun findByDate(date: LocalDate): DailyCreation?
}