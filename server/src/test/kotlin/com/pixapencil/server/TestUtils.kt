package com.pixapencil.server

import org.springframework.test.web.servlet.request.RequestPostProcessor

fun timeZoneHeader(timeZone: String = "UTC") = RequestPostProcessor { request ->
    request.addHeader("X-Time-Zone", timeZone)
    request
}
