package com.therealbluepandabear.pixapencil.utility.compat

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateTimeCompat {
    private const val dateTimeFormatPattern = "MMMM dd, yyyy\nHH:mm"

    fun getCompatibleCurrentDateTime(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateTimeFormatPattern))
        } else {
            val simpleDateFormat = SimpleDateFormat(dateTimeFormatPattern, Locale.US)
            simpleDateFormat.format(Date())
        }
    }
}