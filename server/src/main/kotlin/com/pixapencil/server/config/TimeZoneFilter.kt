package com.pixapencil.server.config

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import java.time.ZoneId

// Check that the time zone specified by the user matches those
// defined in the  IANA Time Zone Database (ZoneId.getAvailableZoneIds())
class TimeZoneFilter : Filter {

    companion object {
        private const val EXPECTED_TIME_ZONE_HEADER: String = "X-Time-Zone"
        const val TIME_ZONE_REQUEST_ATTRIBUTE = "timezone"
    }

    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        filterChain: FilterChain
    ) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

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