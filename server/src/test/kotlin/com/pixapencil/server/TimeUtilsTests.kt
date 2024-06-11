package com.pixapencil.server

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TimeUtilsTests {

    @Test
    fun `should return 'just now'`() {
        val now = LocalDateTime.now()
        assertEquals("just now", formatTimeSince(now))
    }

    @Test
    fun `should return 'minutes ago'`() {
        val fiveMinAgo = LocalDateTime.now().minusMinutes(5)
        assertEquals("5 minutes ago", formatTimeSince(fiveMinAgo))
    }

    @Test
    fun `should return 'hours ago'`() {
        val threeHoursAgo = LocalDateTime.now().minusHours(3)
        assertEquals("3 hours ago", formatTimeSince(threeHoursAgo))
    }

    @Test
    fun `should return 'day ago'`() {
        val oneDayAgo = LocalDateTime.now().minusDays(1)
        assertEquals("1 day ago", formatTimeSince(oneDayAgo))
    }

    @Test
    fun `should return 'months ago'`() {
        val sevMonthsAgo = LocalDateTime.now().minusMonths(7)
        assertEquals("7 months ago", formatTimeSince(sevMonthsAgo))
    }

    @Test
    fun `should return 'year ago'`() {
        val yearAgo = LocalDateTime.now().minusYears(1)
        assertEquals("1 year ago", formatTimeSince(yearAgo))
    }

    @Test
    fun `should return formatted upload date`() {
        val dt = LocalDateTime.of(2024, 6, 10, 15, 30)
        val readable = formatUploadDate(dt)

        assertEquals(readable, "Monday, June 10, 2024 3:30 PM")
    }
}
