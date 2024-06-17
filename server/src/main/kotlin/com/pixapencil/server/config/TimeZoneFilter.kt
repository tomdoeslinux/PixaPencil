package com.pixapencil.server.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.time.ZoneId

// Check that the time zone specified by the user matches those
// defined in the  IANA Time Zone Database (ZoneId.getAvailableZoneIds())
class TimeZoneFilter : OncePerRequestFilter() {

    companion object {
        private const val EXPECTED_TIME_ZONE_HEADER: String = "X-Time-Zone"
        const val TIME_ZONE_REQUEST_ATTRIBUTE = "timezone"
    }

    override fun doFilterInternal(
        httpRequest: HttpServletRequest,
        httpResponse: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val timeZoneId = httpRequest.getHeader(EXPECTED_TIME_ZONE_HEADER)

        if (!ZoneId.getAvailableZoneIds().contains(timeZoneId)) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST)
            return
        }

        // For easy retrieval
        RequestContextHolder.currentRequestAttributes()
            .setAttribute(TIME_ZONE_REQUEST_ATTRIBUTE, timeZoneId, RequestAttributes.SCOPE_REQUEST)

        filterChain.doFilter(httpRequest, httpResponse)
    }
}