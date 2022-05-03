package com.therealbluepandabear.pixapencil.data.local.pixelartcreationsdaotests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.therealbluepandabear.pixapencil.dao.PixelArtCreationsDao
import com.therealbluepandabear.pixapencil.database.PixelArtDatabase
import com.therealbluepandabear.pixapencil.getOrAwaitValue
import com.therealbluepandabear.pixapencil.models.PixelArt
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test completion summary for `PixelArtCreationsDeletionTests`:
 *
 * **Last completion of tests in this package file:**
 *
 * - 2022-05-02 21:09 (11/12 passed) |FAIL| on API 32
 *
 * **Last completion of tests in this package file for API 32:**
 * - 2022-05-02 21:09 (11/12 passed) |FAIL|
 */

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest

class PixelArtCreationsDeletionTests {
    private lateinit var database: PixelArtDatabase
    private lateinit var dao: PixelArtCreationsDao

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PixelArtDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.pixelArtCreationsDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insert5PixelArtCreations_deleteItems_assertSizeChanges() {
        runTest {
            for (i in 1..5) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            dao.deletePixelArtCreation(1)
            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 4)

            dao.deletePixelArtCreation(2)
            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 3)

            dao.deletePixelArtCreation(3)
            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 2)

            dao.deletePixelArtCreation(4)
            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 1)

            dao.deletePixelArtCreation(5)
            assert(dao.getAllPixelArtCreations().getOrAwaitValue().isEmpty())
        }
    }

    @Test
    fun insert16PixelArtCreations_deleteHalf_assertSize8() {
        runTest {
            for (i in 1..16) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..8) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 8)
        }
    }

    @Test
    fun insert32PixelArtCreations_deleteHalf_assertSize16() {
        runTest {
            for (i in 1..32) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..16) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 16)
        }
    }

    @Test
    fun insert64PixelArtCreations_deleteHalf_assertSize32() {
        runTest {
            for (i in 1..64) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..32) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 32)
        }
    }

    @Test
    fun insert128PixelArtCreations_deleteHalf_assertSize64() {
        runTest {
            for (i in 1..128) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..64) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 64)
        }
    }

    @Test
    fun insert256PixelArtCreations_deleteHalf_assertSize128() {
        runTest {
            for (i in 1..256) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..128) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 128)
        }
    }

    @Test
    fun insert512PixelArtCreations_deleteHalf_assertSize256() {
        runTest {
            for (i in 1..512) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..256) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 256)
        }
    }

    @Test
    fun insert1024PixelArtCreations_deleteHalf_assertSize512() {
        runTest {
            for (i in 1..1024) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..512) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 512)
        }
    }

    @Test
    fun insert2048PixelArtCreations_deleteHalf_assertSize1024() {
        runTest {
            for (i in 1..2048) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..1024) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 1024)
        }
    }

    @Test
    fun insert4096PixelArtCreations_deleteHalf_assertSize2048() {
        runTest {
            for (i in 1..4096) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..2048) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 2048)
        }
    }

    @Test
    fun insert8192PixelArtCreations_deleteHalf_assertSize4096() {
        runTest {
            for (i in 1..8192) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..4096) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 4096)
        }
    }
}

