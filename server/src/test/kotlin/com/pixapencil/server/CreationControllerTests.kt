package com.pixapencil.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.pixapencil.server.domain.Creation
import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.GetCreationDTO
import com.pixapencil.server.dtos.UploadCreationDTO
import com.pixapencil.server.dtos.toGetCreationDTO
import com.pixapencil.server.services.AuthUser
import com.pixapencil.server.services.CreationService
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.RequestPostProcessor
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CreationControllerTests {

    private val authContext = AuthUser(User(
        username = "user",
        email = "user@gmail.com",
        password = "password",
        profilePictureUrl = "https://example.com/profile/dummyUser.jpg",
        isVerified = true
    ))

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @MockkBean
    lateinit var creationService: CreationService

    private fun getDummyCreations(): List<GetCreationDTO> {
        val dummyDate = LocalDateTime.of(2024, Month.AUGUST, 23, 15, 12)

        val user1 = User(
            id = 1L,
            username = "Alice",
            profilePictureUrl = "https://picsum.photos/200",
            email = "alice@gmail.com",
            password = "password123"
        )
        val user2 = User(
            id = 2L,
            username = "Bob",
            profilePictureUrl = "https://picsum.photos/200",
            email = "bob@gmail.com",
            password = "password123"
        )

        val dummyCreations = listOf(
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

        return dummyCreations.map { it.toGetCreationDTO(authContext.user, timeZone = ZoneId.of("UTC")) }
    }

    @Test
    fun `returns proper creations`() {
        val creations = getDummyCreations()

        val pageable = PageRequest.of(0, 30)
        val page = PageImpl(creations, pageable, creations.size.toLong())

        every { creationService.getCreations(pageable) } returns page

        val performAction = mockMvc.get("/api/creations/gallery") {
            with(timeZoneHeader())
            param("page", "0")
        }

        creations.forEachIndexed { index, creation ->
            performAction.andExpect {
                jsonPath("$.content[$index].id").value(creation.id)
                jsonPath("$.content[$index].title").value(creation.title)
                jsonPath("$.content[$index].description").value(creation.description)
                jsonPath("$.content[$index].imageUrl").value(creation.imageUrl)
                jsonPath("$.content[$index].likeCount").value(creation.likeCount)
                jsonPath("$.content[$index].isLiked").value(creation.isLiked)
                jsonPath("$.content[$index].author.username").value(creation.author.username)
                jsonPath("$.content[$index].author.profilePictureUrl").value(creation.author.profilePictureUrl)
                jsonPath("$.content[$index].uploadDate").value(creation.uploadDate)
                jsonPath("$.content[$index].timeSince").value(creation.timeSince)
            }
        }

        verify { creationService.getCreations(pageable) }
    }

    @Test
    fun `returns proper single creation`() {
        val dummyCreation = getDummyCreations().first()
        every { creationService.getCreation(1, authContext.user) } returns getDummyCreations().first()

        mockMvc.get("/api/creations/1") {
            with(timeZoneHeader())
            with(user(authContext))
        }.andExpect {
            jsonPath("$.id").value(dummyCreation.id)
            jsonPath("$.title").value(dummyCreation.title)
            jsonPath("$.description").value(dummyCreation.description)
            jsonPath("$.imageUrl").value(dummyCreation.imageUrl)
            jsonPath("$.likeCount").value(dummyCreation.likeCount)
            jsonPath("$.isLiked").value(dummyCreation.isLiked)
            jsonPath("$.author.username").value(dummyCreation.author.username)
            jsonPath("$.author.profilePictureUrl").value(dummyCreation.author.profilePictureUrl)
            jsonPath("$.uploadDate").value(dummyCreation.uploadDate)
            jsonPath("$.timeSince").value(dummyCreation.timeSince)
        }

        verify { creationService.getCreation(1, authContext.user) }
    }

    @Test
    fun `likes a creation without errors`() {
        every { creationService.likeCreation(1, authContext.user) } returns Unit

        mockMvc.post("/api/creations/1/like") {
            with(timeZoneHeader())
            with(user(authContext))
        }.andExpect {
            status { isOk() }
        }

        verify { creationService.likeCreation(1, authContext.user) }
    }

    @Test
    fun `unlikes a creation without errors`() {
        every { creationService.unlikeCreation(1, authContext.user) } returns Unit

        mockMvc.post("/api/creations/1/unlike") {
            with(timeZoneHeader())
            with(user(authContext))
        }.andExpect {
            status { isOk() }
        }

        verify { creationService.unlikeCreation(1, authContext.user) }
    }

    @Test
    fun `deletes a creation without errors`() {
        every { creationService.deleteCreation(1) } returns Unit

        mockMvc.delete("/api/creations/1") {
            with(timeZoneHeader())
            with(user(authContext))
        }.andExpect {
            status { isOk() }
        }

        verify { creationService.deleteCreation(1) }
    }

    @Test
    fun `gets signed creation upload url`() {
        val mimeType = "image/png"
        val uploadUrlResponse = mapOf("url" to "https://upload.com", "key" to "123")
        every { creationService.getCreationUploadUrl(mimeType) } returns uploadUrlResponse

        mockMvc.get("/api/creations/get-upload-url") {
            with(timeZoneHeader())
            with(user(authContext))
            param("mimeType", mimeType)
        }.andExpect {
            status { isOk() }
            jsonPath("$.url").value(uploadUrlResponse["url"])
            jsonPath("$.key").value(uploadUrlResponse["key"])
        }

        verify { creationService.getCreationUploadUrl(mimeType) }
    }

    @Test
    fun `uploads a creation without errors`() {
        val uploadCreation = UploadCreationDTO(
            title = "Dummy Creation",
            description = "Dummy Description",
            imageKey = "dummyImgKey"
        )
        every { creationService.uploadCreation(uploadCreation, authContext.user) } returns Unit

        val jsonUploadCreation = mapper.writeValueAsString(uploadCreation)

        mockMvc.post("/api/creations/upload") {
            with(timeZoneHeader())
            with(user(authContext))
            contentType = MediaType.APPLICATION_JSON
            content = jsonUploadCreation
        }.andExpect {
            status { isOk() }
        }

        verify { creationService.uploadCreation(uploadCreation, authContext.user) }
    }
}