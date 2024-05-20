package com.pixapencil.server.services

import io.awspring.cloud.s3.S3Template
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*

@Service
class S3Service(private val s3Template: S3Template) {

    fun generateRandomKey(mimeType: String): String {
        val supportedMimeTypes = setOf("image/jpeg", "image/png")

        if (mimeType !in supportedMimeTypes) {
            throw IllegalArgumentException("Unsupported MIME type: $mimeType")
        }

        val ext = when (mimeType) {
            "image/jpeg" -> "jpg"
            "image/png" -> "png"
            else -> throw IllegalArgumentException("Unsupported mime type")
        }

        return "${System.currentTimeMillis()}-${UUID.randomUUID()}.$ext"
    }

    fun createSignedPutURL(key: String, bucketName: String = "pixapencil-gallery"): String =
        s3Template.createSignedPutURL(bucketName, key, Duration.ofMinutes(1)).toString()
}