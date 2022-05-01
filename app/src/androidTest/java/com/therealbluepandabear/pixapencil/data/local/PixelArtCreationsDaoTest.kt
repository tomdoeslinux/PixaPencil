package com.therealbluepandabear.pixapencil.data.local

import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.therealbluepandabear.pixapencil.converters.BitmapConverter
import com.therealbluepandabear.pixapencil.dao.PixelArtCreationsDao
import com.therealbluepandabear.pixapencil.database.PixelArtDatabase
import com.therealbluepandabear.pixapencil.getOrAwaitValue
import com.therealbluepandabear.pixapencil.models.PixelArt
import com.therealbluepandabear.pixapencil.utility.DateTimeCompatibilityUtilities
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest

class PixelArtCreationsDaoTest {
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
    fun insertPixelArtCreation_assertObjId() {
        runTest {
            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.objId } returns 1 }

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().first().objId == pixelArtCreation.objId)
        }
    }

    @Test
    fun insertPixelArtCreation_assertTitle() {
        runTest {
            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.title } returns "PixelArtCreation" }

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().first().title == pixelArtCreation.title)
        }
    }

    @Test
    fun insertPixelArtCreation_assertWidth() {
        runTest {
            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.width } returns 5 }

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().first().width == pixelArtCreation.width)
        }
    }

    @Test
    fun insertPixelArtCreation_assertHeight() {
        runTest {
            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.height } returns 5 }

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().first().height == pixelArtCreation.height)
        }
    }

    @Test
    fun insertPixelArtCreation_assertDimenCW() {
        runTest {
            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.dimenCW } returns 5 }

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().first().dimenCW == pixelArtCreation.dimenCW)
        }
    }

    @Test
    fun insertPixelArtCreation_assertDimenCH() {
        runTest {
            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.dimenCH } returns 5 }

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().first().dimenCH == pixelArtCreation.dimenCH)
        }
    }

    @Test
    fun insertPixelArtCreation_assertRotation() {
        runTest {
            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.rotation } returns 90f }

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().first().rotation == pixelArtCreation.rotation)
        }
    }

    @Test
    fun insertPixelArtCreation_assertStarred() {
        runTest {
            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.starred } returns false }

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().first().starred == pixelArtCreation.starred)
        }
    }

    @Test
    fun insertPixelArtCreation_assertDateCreated() {
        runTest {
            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.dateCreated } returns DateTimeCompatibilityUtilities.getCompatibleCurrentDateTime() }

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().first().dateCreated == pixelArtCreation.dateCreated)
        }
    }

    @Test
    fun insertPixelArtCreation_assertBitmap() {
        runTest {
            val bitmapObj = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888)
            val bitmapString = BitmapConverter.convertBitmapToString(bitmapObj)

            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.bitmap } returns bitmapString }

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().first().bitmap == pixelArtCreation.bitmap)
        }
    }

    @Test
    fun insertPixelArtCreation_assertBitmapProperties() {
        runTest {
            val bitmapObj = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888)
            val bitmapString = BitmapConverter.convertBitmapToString(bitmapObj)

            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also { every { it.bitmap } returns bitmapString }

            dao.insertPixelArt(pixelArtCreation)

            val bitmapStringFromDB: String = dao.getAllPixelArtCreations().getOrAwaitValue().first().bitmap
            val bitmapObjFromDB: Bitmap = BitmapConverter.convertStringToBitmap(bitmapStringFromDB)!!

            assert(bitmapObjFromDB.width == bitmapObj.width)
            assert(bitmapObjFromDB.height == bitmapObj.height)
            assert(bitmapObjFromDB.config == bitmapObj.config)
        }
    }

    @Test
    fun insertPixelArtCreation_assertSize1() {
        runTest {
            val pixelArtCreation = mockk<PixelArt>(relaxed = true)

            dao.insertPixelArt(pixelArtCreation)

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 1)
        }
    }

    @Test
    fun insert5PixelArtCreations_assertSize5() {
        runTest {
            for (i in 1..5) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 5)
        }
    }

    @Test
    fun insert25PixelArtCreations_assertSize25() {
        runTest {
            for (i in 1..25) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 25)
        }
    }

    @Test
    fun insert50PixelArtCreations_assertSize50() {
        runTest {
            for (i in 1..50) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 50)
        }
    }

    @Test
    fun insert100PixelArtCreations_assertSize100() {
        runTest {
            for (i in 1..100) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 100)
        }
    }

    @Test
    fun insert250PixelArtCreations_assertSize250() {
        runTest {
            for (i in 1..250) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 250)
        }
    }

    @Test
    fun insert500PixelArtCreations_assertSize500() {
        runTest {
            for (i in 1..500) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 500)
        }
    }

    @Test
    fun insert1_000PixelArtCreations_assertSize1_000() {
        runTest {
            for (i in 1..1_000) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 1_000)
        }
    }

    @Test
    fun insert2_000PixelArtCreations_assertSize2_000() {
        runTest {
            for (i in 1..2_000) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 2_000)
        }
    }

    @Test
    fun insert4_000PixelArtCreations_assertSize4_000() {
        runTest {
            for (i in 1..4_000) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 4_000)
        }
    }

    @Test
    fun insert8_000PixelArtCreations_assertSize8_000() {
        runTest {
            for (i in 1..8_000) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 8_000)
        }
    }

    @Test
    fun insert16_000PixelArtCreations_assertSize16_000() {
        runTest {
            for (i in 1..16_000) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                dao.insertPixelArt(pixelArtCreation)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 16_000)
        }
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

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 128)
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

    @Test
    fun insert16384PixelArtCreations_deleteHalf_assertSize8192() {
        runTest {
            for (i in 1..16384) {
                val pixelArtCreation = mockk<PixelArt>(relaxed = true)
                every { pixelArtCreation.objId } returns i

                dao.insertPixelArt(pixelArtCreation)
            }

            for (i in 1..8192) {
                dao.deletePixelArtCreation(i)
            }

            assert(dao.getAllPixelArtCreations().getOrAwaitValue().size == 8192)
        }
    }
}
