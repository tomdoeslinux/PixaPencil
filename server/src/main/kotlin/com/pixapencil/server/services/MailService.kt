package com.pixapencil.server.services

import freemarker.template.Configuration
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils
import java.nio.charset.StandardCharsets
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

data class PxpMail(
    val from: String,
    val to: String,
    val subject: String,
    val body: String,
    val isHtml: Boolean,
)

@Service
class MailService(
    private val javaMailSender: JavaMailSender,
    @Qualifier("applicationTaskExecutor") private val executor: Executor,
    private val freemarkerConfig: Configuration,
) {
    fun sendVerificationMail(
        to: String,
        verifLink: String,
    ) {
        val htmlContent = templateToString("verify-email.ftl", mapOf("verifLink" to verifLink))
        val mail = PxpMail(from = "todoescode@gmail.com", to = to, subject = "Verify your account", body = htmlContent, isHtml = true)

        sendMail(mail)
    }

    private fun sendMail(mail: PxpMail) {
        CompletableFuture.runAsync({
            val message = javaMailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name())
            helper.setText(mail.body, mail.isHtml)
            helper.setSubject(mail.subject)
            helper.setFrom(mail.from)
            helper.setTo(mail.to)
            javaMailSender.send(message)
        }, executor)
    }

    private fun templateToString(
        templateLocation: String,
        model: Map<String, Any>,
    ): String {
        return FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate(templateLocation), model)
    }
}
