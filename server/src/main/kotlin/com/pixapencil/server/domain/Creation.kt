package com.pixapencil.server.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class Creation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false, length = 5000)
    var description: String,

    @Column(nullable = false)
    var coverImageUrl: String,

    @CreationTimestamp
    var createdAt: LocalDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,
)