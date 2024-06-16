package com.pixapencil.server

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

private fun convertLocalDateTimeToSpecifiedTZ(dateTime: LocalDateTime, timeZone: ZoneId) =
    dateTime
        .atZone(ZoneId.systemDefault())
        .withZoneSameInstant(timeZone)
        .toLocalDateTime()

/*
In this method, we take a specific moment in time and format the duration that has elapsed since that moment.
It is time zone agnostic, we convert both the date parameter and the current time to UTC.
*/
fun formatTimeSinceUTC(dateUTC: LocalDateTime): String {
    val nowUTC = LocalDateTime.now(ZoneOffset.UTC)

    val duration = Duration.between(dateUTC, nowUTC)
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

// Format the upload date in the user's specified time zone
fun formatUploadDateTZ(dateTime: LocalDateTime, timeZone: ZoneId): String {
    val dateTimeAtTZ = convertLocalDateTimeToSpecifiedTZ(dateTime, timeZone)
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy h:mm a", Locale.ENGLISH)
    return dateTimeAtTZ.format(formatter) + " (" + timeZone + ")"
}