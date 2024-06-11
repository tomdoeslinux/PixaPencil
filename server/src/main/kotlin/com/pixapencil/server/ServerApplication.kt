package com.pixapencil.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// import org.springframework.scheduling.annotation.EnableAsync
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@SpringBootApplication
class ServerApplication

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
