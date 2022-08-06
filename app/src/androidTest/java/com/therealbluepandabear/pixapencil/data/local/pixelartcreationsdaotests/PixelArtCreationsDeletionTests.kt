package com.therealbluepandabear.pixapencil.data.local.pixelartcreationsdaotests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.therealbluepandabear.pixapencil.dao.PixelArtCreationsDao
import com.therealbluepandabear.pixapencil.database.PixelArtDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

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
        dao = database.dao()
    }

    @After
    fun tearDown() {
        database.close()
    }
//
//    @Test
//    fun insert5PixelArtCreations_deleteItems_assertSizeChanges() {
//        runTest {
//            for (i in 1..5) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            dao.delete(1)
//            assert(dao.getAll().getOrAwaitValue().size == 4)
//
//            dao.delete(2)
//            assert(dao.getAll().getOrAwaitValue().size == 3)
//
//            dao.delete(3)
//            assert(dao.getAll().getOrAwaitValue().size == 2)
//
//            dao.delete(4)
//            assert(dao.getAll().getOrAwaitValue().size == 1)
//
//            dao.delete(5)
//            assert(dao.getAll().getOrAwaitValue().isEmpty())
//        }
//    }

//    @Test
//    fun insert16PixelArtCreations_deleteHalf_assertSize8() {
//        runTest {
//            for (i in 1..16) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            for (i in 1..8) {
//                dao.delete(i)
//            }
//
//            assert(dao.getAll().getOrAwaitValue().size == 8)
//        }
//    }

//    @Test
//    fun insert32PixelArtCreations_deleteHalf_assertSize16() {
//        runTest {
//            for (i in 1..32) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            for (i in 1..16) {
//                dao.delete(i)
//            }
//
//            assert(dao.getAll().getOrAwaitValue().size == 16)
//        }
//    }
//
//    @Test
//    fun insert64PixelArtCreations_deleteHalf_assertSize32() {
//        runTest {
//            for (i in 1..64) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            for (i in 1..32) {
//                dao.delete(i)
//            }
//
//            assert(dao.getAll().getOrAwaitValue().size == 32)
//        }
//    }

//    @Test
//    fun insert128PixelArtCreations_deleteHalf_assertSize64() {
//        runTest {
//            for (i in 1..128) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            for (i in 1..64) {
//                dao.delete(i)
//            }
//
//            assert(dao.getAll().getOrAwaitValue().size == 64)
//        }
//    }

//    @Test
//    fun insert256PixelArtCreations_deleteHalf_assertSize128() {
//        runTest {
//            for (i in 1..256) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            for (i in 1..128) {
//                dao.delete(i)
//            }
//
//            assert(dao.getAll().getOrAwaitValue().size == 128)
//        }
//    }

//    @Test
//    fun insert512PixelArtCreations_deleteHalf_assertSize256() {
//        runTest {
//            for (i in 1..512) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            for (i in 1..256) {
//                dao.delete(i)
//            }
//
//            assert(dao.getAll().getOrAwaitValue().size == 256)
//        }
//    }

//    @Test
//    fun insert1024PixelArtCreations_deleteHalf_assertSize512() {
//        runTest {
//            for (i in 1..1024) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            for (i in 1..512) {
//                dao.delete(i)
//            }
//
//            assert(dao.getAll().getOrAwaitValue().size == 512)
//        }
//    }

//    @Test
//    fun insert2048PixelArtCreations_deleteHalf_assertSize1024() {
//        runTest {
//            for (i in 1..2048) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            for (i in 1..1024) {
//                dao.delete(i)
//            }
//
//            assert(dao.getAll().getOrAwaitValue().size == 1024)
//        }
//    }

//    @Test
//    fun insert4096PixelArtCreations_deleteHalf_assertSize2048() {
//        runTest {
//            for (i in 1..4096) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            for (i in 1..2048) {
//                dao.delete(i)
//            }
//
//            assert(dao.getAll().getOrAwaitValue().size == 2048)
//        }
//    }

//    @Test
//    fun insert8192PixelArtCreations_deleteHalf_assertSize4096() {
//        runTest {
//            for (i in 1..8192) {
//                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
//                every { pixelArtCreation.objId } returns i
//
//                dao.insert(pixelArtCreation)
//            }
//
//            for (i in 1..4096) {
//                dao.delete(i)
//            }
//
//            assert(dao.getAll().getOrAwaitValue().size == 4096)
//        }
//    }
}

