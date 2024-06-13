package com.pixapencil.server.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "verification_tokens")
@Entity
class VerificationToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    @Lob
    var token: String,

    @Column(nullable = false)
    var expiryDate: LocalDateTime,

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,
)
