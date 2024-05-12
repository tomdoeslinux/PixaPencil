package com.pixapencil.server.repos

import com.pixapencil.server.domain.Creation
import org.springframework.data.jpa.repository.JpaRepository

interface CreationRepository : JpaRepository<Creation, Long>