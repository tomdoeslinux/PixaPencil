package com.pixapencil.server.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime


@Table(name = "users")
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

    @Column(nullable = false)
    var profilePictureUrl: String,

    @Column(nullable = false)
    var isVerified: Boolean = false,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "creation_likes",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "creation_id")]
    )
    val likedCreations: MutableList<Creation> = mutableListOf(),
)