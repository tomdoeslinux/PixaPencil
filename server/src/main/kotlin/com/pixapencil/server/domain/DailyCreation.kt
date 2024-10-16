package com.pixapencil.server.domain

import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "daily_creations")
@Entity
class DailyCreation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var date: LocalDate = LocalDate.now(),

    @OneToOne
    @JoinColumn(name = "creation_id", nullable = false)
    var creation: Creation,
)