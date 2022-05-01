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
}
