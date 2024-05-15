package com.pixapencil.server.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var username: String,

    @Column(unique = true, nullable = false)
    var email: String,

    @Column(unique = true, nullable = false)
    @Lob
    var password: String,

    @CreationTimestamp
    var createdAt: LocalDateTime? = null,

    @Column(nullable = false)
    var isVerified: Boolean = false,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    var creations: List<Creation> = mutableListOf()
)