package com.pixapencil.server.services

import io.awspring.cloud.s3.S3Template
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*

@Service
class S3Service(private val s3Template: S3Template) {

    fun createSignedPutURL(mimeType: String): String {
        val ext = when (mimeType) {
            "image/jpeg" -> "jpg"
            "image/png" -> "png"
            else -> throw IllegalArgumentException("Unsupported mime type")
        }

        val key = "${System.currentTimeMillis()}-${UUID.randomUUID()}.$ext"
        return s3Template.createSignedPutURL("pixapencil-gallery", key, Duration.ofMinutes(1)).toString()
    }
}