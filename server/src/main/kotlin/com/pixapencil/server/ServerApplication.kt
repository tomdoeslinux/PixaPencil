package com.pixapencil.server

import com.pixapencil.server.services.MailService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

//import org.springframework.scheduling.annotation.EnableAsync
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@SpringBootApplication
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
