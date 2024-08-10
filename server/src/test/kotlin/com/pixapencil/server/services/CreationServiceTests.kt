package com.pixapencil.server.services

import com.ninjasquad.springmockk.MockkBean
import com.pixapencil.server.authContext
import com.pixapencil.server.domain.Creation
import com.pixapencil.server.domain.User
import com.pixapencil.server.dtos.UploadCreationDTO
import com.pixapencil.server.dtos.toGetCreationDTO
import com.pixapencil.server.repos.CreationRepository
import com.pixapencil.server.repos.UserRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.verify
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime
import java.time.Month
import java.util.concurrent.Executors

@SpringBootTest
@ActiveProfiles("test")
class CreationServiceTests {

    @MockkBean
    private lateinit var s3Service: S3Service

    @MockkBean
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var creationRepository: CreationRepository

    @Autowired
    private lateinit var creationService: CreationService

    private fun getDummyCreations(): List<Creation> {
        val dummyDate = LocalDateTime.of(2024, Month.JULY, 20, 12, 0, 0)

        val creations = listOf(
            Creation(
                id = 1L,
                title = "Sample Title",
                description = "Sample Description",
                imageKey = "key1",
                user = authContext.user,
                likeCount = 0,
                createdAt = dummyDate
            )
        )

        return creations
    }

    private fun likeCreation(creation: Creation, user: User) {
        ++creation.likeCount
        creation.likedBy.add(authContext.user)
        user.likedCreations.add(creation)
    }

    @Test
    fun `getCreations returns paginated results`() {
        val pageable = PageRequest.of(0, 10)

        every { userRepository.findByIdOrNull(1L) } returns authContext.user
        every { creationRepository.findAll(pageable) } returns PageImpl(getDummyCreations())

        val result = creationService.getCreations(pageable)
        val expected = PageImpl(getDummyCreations().map { it.toGetCreationDTO(authContext.user) })

        assertEquals(result, expected)

        verify { userRepository.findByIdOrNull(1L) }
        verify { creationRepository.findAll(pageable) }
    }

    @Test
    fun `getCreations throws EntityNotFoundException if user not found`() {
        val pageable = PageRequest.of(0, 10)
        every { userRepository.findByIdOrNull(1L) } returns null

        assertThrows<EntityNotFoundException> {
            creationService.getCreations(pageable)
        }

        verify { userRepository.findByIdOrNull(1L) }
        verify(exactly = 0) { creationRepository.findAll(pageable) }
    }

    @Test
    fun `getCreationUploadUrl returns valid signed URL and key`() {
        val mimeType = "image/png"
        every { s3Service.generateRandomKey(mimeType) } returns "randomKey"
        every { s3Service.createSignedPutURL("randomKey") } returns "https://signedurl.com"

        val result = creationService.getCreationUploadUrl(mimeType)

        assertEquals("https://signedurl.com", result["url"])
        assertEquals("randomKey", result["key"])
    }

    @Test
    fun `deleteCreation properly deletes creation and associated s3 object`() {
        val dummyCreation = getDummyCreations().first()

        every { creationRepository.findByIdOrNull(1L) } returns dummyCreation
        justRun { creationRepository.delete(dummyCreation) }
        justRun { s3Service.deleteObject(dummyCreation.imageKey) }

        creationService.deleteCreation(1L)

        verify { creationRepository.delete(dummyCreation) }
        verify { s3Service.deleteObject(dummyCreation.imageKey) }
    }

    @Test
    fun `deleteCreation throws EntityNotFoundException when creation does not exist`() {
        every { creationRepository.findByIdOrNull(1L) } returns null

        assertThrows<EntityNotFoundException> {
            creationService.deleteCreation(1L)
        }
    }

    @Test
    fun `uploadCreation saves new creation correctly`() {
        val uploadCreationDTO = UploadCreationDTO(
            title = "New Creation",
            description = "Description of new creation",
            imageKey = "newKey"
        )

        every { userRepository.findByIdOrNull(authContext.getUserId()) } returns authContext.user
        every { creationRepository.save(any()) } answers { firstArg() }

        creationService.uploadCreation(uploadCreationDTO, authContext)

        verify { creationRepository.save(match<Creation> {
            it.title == uploadCreationDTO.title &&
            it.description == uploadCreationDTO.description &&
            it.imageKey == uploadCreationDTO.imageKey &&
                    it.user == authContext.user
        }) }
    }

    @Test
    fun `likeCreation adds user to likedBy and increments likeCount`() {
        val dummyCreation = getDummyCreations().first()

        every { userRepository.findByIdOrNull(authContext.getUserId()) } returns authContext.user
        every { creationRepository.findByIdOrNull(1L) } returns dummyCreation

        // Return value for this function not important
        every { creationRepository.save(any()) } answers { firstArg() }

        creationService.likeCreation(1L, authContext)

        assertTrue(dummyCreation.likedBy.contains(authContext.user))
        assertEquals(dummyCreation.likeCount, 1)

        verify { creationRepository.findByIdOrNull(1L) }
        verify { creationRepository.save(dummyCreation) }
    }

    @Test
    fun `unlikeCreation removes user from likedBy and decrements likeCount`() {
        // Creation needs to be liked first before it can be unliked
        val dummyCreation = getDummyCreations().first()
        likeCreation(dummyCreation, authContext.user)

        every { userRepository.findByIdOrNull(authContext.getUserId()) } returns authContext.user
        every { creationRepository.findByIdOrNull(1L) } returns dummyCreation

        // Return value for this function not important
        every { creationRepository.save(any()) } answers { firstArg() }

        creationService.unlikeCreation(1L, authContext)

        assertFalse(dummyCreation.likedBy.contains(authContext.user))
        assertEquals(dummyCreation.likeCount, 0)

        verify { creationRepository.findByIdOrNull(1L) }
        verify { creationRepository.save(dummyCreation) }
    }

    @Test
    fun `likeCreation handles concurrent likes correctly`() {
        val dummyCreation = getDummyCreations().first()
        every { userRepository.findByIdOrNull(authContext.getUserId()) } returns authContext.user
        every { creationRepository.findByIdOrNull(1L) } returns dummyCreation
        every { creationRepository.save(any()) } answers { firstArg() }

        val numThreads = 10
        val executorService = Executors.newFixedThreadPool(numThreads)

        for (i in 1..numThreads) {
            executorService.submit {
                creationService.likeCreation(1L, authContext)
            }
        }

        executorService.shutdown()
        while (!executorService.isTerminated) {
            Thread.sleep(100)
        }

        // We expect the like count to be exactly 1 because the same user cannot like more than once.
        assertEquals(1, dummyCreation.likeCount)
        assertTrue(dummyCreation.likedBy.contains(authContext.user))

        verify { creationRepository.findByIdOrNull(1L) }
        verify(exactly = 1) { creationRepository.save(dummyCreation) }
    }

    @Test
    fun `unlikeCreation handles concurrent likes correctly`() {
        // Creation needs to be liked first before it can be unliked
        val dummyCreation = getDummyCreations().first()
        likeCreation(dummyCreation, authContext.user)

        every { userRepository.findByIdOrNull(authContext.getUserId()) } answers { authContext.user }
        every { creationRepository.findByIdOrNull(1L) } returns dummyCreation
        every { creationRepository.save(any()) } answers { firstArg() }

        val numThreads = 10
        val executorService = Executors.newFixedThreadPool(numThreads)

        for (i in 1..numThreads) {
            executorService.submit {
                creationService.unlikeCreation(1L, authContext)
            }
        }

        executorService.shutdown()
        while (!executorService.isTerminated) {
            Thread.sleep(100)
        }

        // We expect the like count to be exactly 1 because the same user cannot like more than once.
        assertEquals(0, dummyCreation.likeCount)
        assertFalse(dummyCreation.likedBy.contains(authContext.user))

        verify { creationRepository.findByIdOrNull(1L) }
        verify(exactly = 1) { creationRepository.save(dummyCreation) }
    }
}