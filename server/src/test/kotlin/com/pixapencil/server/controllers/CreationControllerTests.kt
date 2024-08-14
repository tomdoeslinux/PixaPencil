package com.pixapencil.server.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.pixapencil.server.domain.Creation
import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.UploadCreationDTO
import com.pixapencil.server.repos.CreationRepository
import com.pixapencil.server.repos.UserRepository
import com.pixapencil.server.services.AuthUser
import com.pixapencil.server.services.S3Service
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CreationControllerTests {

    @Autowired
    lateinit var creationRepository: CreationRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @MockkBean
    lateinit var s3Service: S3Service

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    lateinit var commonUser: User
    lateinit var dummyCreationsDependentUsers: List<User>
    lateinit var dummyCreations: List<Creation>
    lateinit var dummyCreation: Creation

    @BeforeEach
    fun setUp() {
        commonUser = User(
            username = "user",
            email = "user@example.com",
            password = passwordEncoder.encode("password"),
            profilePictureUrl = "https://example.com/profile/dummyUser.jpg",
            isVerified = true
        )

        dummyCreation = Creation(
            id = 1L,
            title = "Sunset over the hills",
            description = "A beautiful depiction of a sunset",
            imageKey = "img1",
            likeCount = 120,
            createdAt = LocalDateTime.now().minusDays(2),
            user = commonUser,
            likedBy = mutableListOf(commonUser)
        )

        dummyCreationsDependentUsers = listOf(
            User(
                id = 1L,
                username = "Alice",
                profilePictureUrl = "https://picsum.photos/200",
                email = "alice@gmail.com",
                password = passwordEncoder.encode("password123")
            ),
            User(
                id = 2L,
                username = "Bob",
                profilePictureUrl = "https://picsum.photos/200",
                email = "bob@gmail.com",
                password = passwordEncoder.encode("password123")
            )
        )

        val dummyDate = LocalDateTime.of(2024, Month.AUGUST, 23, 15, 12)

        val user1 = dummyCreationsDependentUsers[0]
        val user2 = dummyCreationsDependentUsers[1]

        dummyCreations = listOf(
            Creation(
                id = 1L,
                title = "Sunset over the hills",
                description = "A beautiful depiction of a sunset",
                imageKey = "img1",
                likeCount = 120,
                createdAt = dummyDate.minusDays(2),
                user = user1,
                likedBy = mutableListOf(user1)
            ),
            Creation(
                id = 2L,
                title = "Mountain Peak",
                description = "The majestic mountain peak",
                imageKey = "img2",
                likeCount = 85,
                createdAt = dummyDate.minusDays(1),
                user = user2,
                likedBy = mutableListOf()
            ),
            Creation(
                id = 3L,
                title = "Ocean Breeze",
                description = "Feel the breeze from the ocean",
                imageKey = "img3",
                likeCount = 150,
                createdAt = dummyDate.minusHours(12),
                user = user1,
                likedBy = mutableListOf(user1)
            ),
            Creation(
                id = 4L,
                title = "City Lights",
                description = "The city illuminated at night",
                imageKey = "img4",
                likeCount = 200,
                createdAt = dummyDate.minusHours(6),
                user = user2,
                likedBy = mutableListOf(user1, user2)
            ),
            Creation(
                id = 5L,
                title = "Forest Path",
                description = "A serene path through the forest",
                imageKey = "img5",
                likeCount = 110,
                createdAt = dummyDate.minusHours(3),
                user = user1,
                likedBy = mutableListOf()
            ),
            Creation(
                id = 6L,
                title = "Desert Mirage",
                description = "A mirage in the vast desert",
                imageKey = "img6",
                likeCount = 95,
                createdAt = dummyDate.minusHours(1),
                user = user2,
                likedBy = mutableListOf(user2)
            ),
            Creation(
                id = 7L,
                title = "Snowy Landscape",
                description = "Snow-covered trees and hills",
                imageKey = "img7",
                likeCount = 130,
                createdAt = dummyDate.minusMinutes(30),
                user = user1,
                likedBy = mutableListOf(user1)
            ),
            Creation(
                id = 8L,
                title = "Rainforest Canopy",
                description = "Lush greenery of the rainforest",
                imageKey = "img8",
                likeCount = 140,
                createdAt = dummyDate.minusMinutes(15),
                user = user2,
                likedBy = mutableListOf()
            ),
            Creation(
                id = 9L,
                title = "Volcano Eruption",
                description = "A powerful volcanic eruption",
                imageKey = "img9",
                likeCount = 160,
                createdAt = dummyDate.minusMinutes(5),
                user = user1,
                likedBy = mutableListOf(user1, user2)
            ),
            Creation(
                id = 10L,
                title = "Waterfall",
                description = "A majestic waterfall",
                imageKey = "img10",
                likeCount = 175,
                createdAt = dummyDate.minusMinutes(1),
                user = user2,
                likedBy = mutableListOf(user2)
            )
        )
    }


    companion object {
        const val AWS_DUMMY_KEY = "dummy_key"
        const val AWS_DUMMY_SIGNED_PUT_URL = "https://dummyurl.com/upload"
        const val AWS_URL = "https://pixapencil-gallery.s3.ap-southeast-2.amazonaws.com/"
    }

    @BeforeEach
    fun setup() {
        // We don't want to actually interact with S3, as it is computationally expensive and may cost $$
        every { s3Service.generateRandomKey(any()) } returns AWS_DUMMY_KEY
        every { s3Service.createSignedPutURL(any(), any()) } returns AWS_DUMMY_SIGNED_PUT_URL
        every { s3Service.deleteObject(any(), any()) } just runs
    }


    @Test
    fun `returns proper creations from gallery`() {
        // Setup
        userRepository.save(commonUser)
        userRepository.saveAll(dummyCreationsDependentUsers)
        creationRepository.saveAll(dummyCreations)

        // Test
        val creations = dummyCreations

        val performAction = mockMvc.get("/api/creations/gallery") {
            param("page", "0")
        }

        creations.forEachIndexed { index, creation ->
            performAction.andExpect {
                jsonPath("$.content[$index].id").value(creation.id)
                jsonPath("$.content[$index].title").value(creation.title)
                jsonPath("$.content[$index].description").value(creation.description)
                jsonPath("$.content[$index].imageUrl").value("$AWS_URL$AWS_DUMMY_KEY")
                jsonPath("$.content[$index].likeCount").value(creation.likeCount)
                jsonPath("$.content[$index].isLiked").value(false)
                jsonPath("$.content[$index].author.username").value(creation.user.username)
                jsonPath("$.content[$index].profilePictureUrl").value("${AWS_URL}profile_pic.png") // default for now
                jsonPath("$.uploadDate").value(creation.createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) // e.g. 2024-08-02T00:27:46
            }
        }
    }

    @Test
    fun `returns proper single creation`() {
        // Setup
        userRepository.save(commonUser)
        userRepository.saveAll(dummyCreationsDependentUsers)
        creationRepository.save(dummyCreation)

        // Test
        val dummyCreation = dummyCreations.first()

        mockMvc.get("/api/creations/1") {
            with(user(AuthUser(commonUser)))
        }.andExpect {
            jsonPath("$.id").value(dummyCreation.id)
            jsonPath("$.title").value(dummyCreation.title)
            jsonPath("$.description").value(dummyCreation.description)
            jsonPath("$.imageUrl").value("$AWS_URL$AWS_DUMMY_KEY")
            jsonPath("$.likeCount").value(dummyCreation.likeCount)
            jsonPath("$.isLiked").value(false) // current auth context hasn't liked this creation
            jsonPath("$.author.username").value(dummyCreation.user.username)
            jsonPath("$.author.profilePictureUrl").value("${AWS_URL}profile_pic.png") // default for now
            jsonPath("$.uploadDate").value(dummyCreation.createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) // e.g. 2024-08-02T00:27:46
        }
    }

    @Test
    fun `likes a creation without errors`() {
        // Setup (Make sure user didn't like this creation)
        commonUser.likedCreations.clear()
        dummyCreation.likedBy.clear()
        userRepository.save(commonUser)
        userRepository.saveAll(dummyCreationsDependentUsers)
        creationRepository.save(dummyCreation)

        // Test
        mockMvc.post("/api/creations/1/like") {
            with(user(AuthUser(commonUser)))
        }.andExpect {
            status { isOk() }
        }

        // Verify DB state
        val creations = creationRepository.findAll()
        assertTrue(creations.first().likeCount == (dummyCreation.likeCount + 1))
    }

    @Test
    fun `unlikes a creation without errors`() {
        // Setup
        userRepository.save(commonUser)
        userRepository.saveAll(dummyCreationsDependentUsers)
        creationRepository.save(dummyCreation)

        // Test
        mockMvc.post("/api/creations/1/unlike") {
            with(user(AuthUser(commonUser)))
        }.andExpect {
            status { isOk() }
        }

        // Verify DB state
        val creations = creationRepository.findAll()
        assertTrue(creations.first().likeCount == (dummyCreation.likeCount - 1))
    }

    @Test
    fun `deletes a creation without errors`() {
        // Setup
        userRepository.save(commonUser)
        userRepository.saveAll(dummyCreationsDependentUsers)
        creationRepository.save(dummyCreation)

        // Test
        mockMvc.delete("/api/creations/${dummyCreation.id}") {
            with(user(AuthUser(commonUser)))
        }.andExpect {
            status { isOk() }
        }

        // Verify DB state
        val creations = creationRepository.findAll()
        assertTrue(creations.isEmpty())
    }

    @Test
    fun `gets signed creation upload url`() {
        // Setup
        userRepository.save(commonUser)

        // Test
        val mimeType = "image/png"
        val uploadUrlResponse = mapOf("url" to "https://upload.com", "key" to "123")

        mockMvc.get("/api/creations/get-upload-url") {
            with(user(AuthUser(commonUser)))
            param("mimeType", mimeType)
        }.andExpect {
            status { isOk() }
            jsonPath("$.url").value(uploadUrlResponse["url"])
            jsonPath("$.key").value(uploadUrlResponse["key"])
        }
    }

    @Test
    fun `uploads a creation without errors`() {
        // Setup
        userRepository.save(commonUser)

        // Test
        val uploadCreation = UploadCreationDTO(
            title = "Dummy Creation",
            description = "Dummy Description",
            imageKey = AWS_DUMMY_KEY
        )

        val jsonUploadCreation = mapper.writeValueAsString(uploadCreation)

        mockMvc.post("/api/creations/upload") {
            with(user(AuthUser(commonUser)))
            contentType = MediaType.APPLICATION_JSON
            content = jsonUploadCreation
        }.andExpect {
            status { isOk() }
        }

        // Verify DB state
        val creations = creationRepository.findAll()
        assertTrue(creations.isNotEmpty())

        val savedCreation = creations.first()
        assertEquals(savedCreation.title, "Dummy Creation")
        assertEquals(savedCreation.description, "Dummy Description")
    }
}