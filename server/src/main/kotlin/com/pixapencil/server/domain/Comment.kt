package com.pixapencil.server.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLInsert
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Table(name = "comments")
@Entity
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 1000)
    var text: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne
    @JoinColumn(name = "creation_id")
    var creation: Creation,

    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now(),
)