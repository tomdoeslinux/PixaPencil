package com.pixapencil.server

import com.pixapencil.server.domain.User
import com.pixapencil.server.services.AuthUser

val authContext = AuthUser(User(
    id = 1L,
    username = "user",
    email = "user@gmail.com",
    password = "password",
    profilePictureUrl = "https://example.com/profile/dummyUser.jpg",
    isVerified = true
))
