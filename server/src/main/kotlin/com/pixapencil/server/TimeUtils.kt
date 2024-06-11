package com.pixapencil.server

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun formatTimeSince(date: LocalDateTime): String {
    val now = LocalDateTime.now()

    val duration = Duration.between(date, now)
    val seconds = duration.seconds

    val intervals = mapOf(
        "year" to 31_536_000,
        "month" to 2_592_000,
        "day" to 86_400,
        "hour" to 3_600,
        "minute" to 60
    )

    for ((label, intervalSeconds) in intervals) {
        val div = seconds / intervalSeconds

        if (div >= 1) {
            return "$div ${label}${if (div > 1) "s" else ""} ago"
        }
    }

    // If time diff is less than a minute
    return "just now"
}

fun formatUploadDate(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy h:mm a", Locale.ENGLISH)
    return dateTime.format(formatter)
}