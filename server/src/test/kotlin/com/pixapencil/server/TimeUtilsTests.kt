package com.pixapencil.server

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class TimeUtilsTests {

    private fun getUTCTimeNow() = LocalDateTime.now(ZoneOffset.UTC)

    @Test
    fun `should return 'just now'`() {
        val now = getUTCTimeNow()
        assertEquals("just now", formatTimeSinceUTC(now))
    }

    @Test
    fun `should return 'minutes ago'`() {
        val fiveMinAgo = getUTCTimeNow().minusMinutes(5)
        assertEquals("5 minutes ago", formatTimeSinceUTC(fiveMinAgo))
    }

    @Test
    fun `should return 'hours ago'`() {
        val threeHoursAgo = getUTCTimeNow().minusHours(3)
        assertEquals("3 hours ago", formatTimeSinceUTC(threeHoursAgo))
    }

    @Test
    fun `should return 'day ago'`() {
        val oneDayAgo = getUTCTimeNow().minusDays(1)
        assertEquals("1 day ago", formatTimeSinceUTC(oneDayAgo))
    }

    @Test
    fun `should return 'months ago'`() {
        val sevMonthsAgo = getUTCTimeNow().minusMonths(7)
        assertEquals("7 months ago", formatTimeSinceUTC(sevMonthsAgo))
    }

    @Test
    fun `should return 'year ago'`() {
        val yearAgo = getUTCTimeNow().minusYears(1)
        assertEquals("1 year ago", formatTimeSinceUTC(yearAgo))
    }

    @Test
    fun `should return formatted upload date`() {
        val dt = LocalDateTime.of(2024, 6, 10, 15, 30)
        val readable = formatUploadDateTZ(dt, ZoneId.of("Pacific/Auckland"))

        assertEquals(readable, "Monday, June 10, 2024 3:30 PM (Pacific/Auckland)")
    }
}
