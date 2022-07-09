package com.therealbluepandabear.pixapencil.data.local.pixelartcreationsdaotests

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

class PixelArtCreationsReplacementTests {
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

//    @Test
//    fun insertPixelArtCreation_assertBitmapDoesReplace() {
//        runTest {
//            val bitmapObj = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888)
//            val bitmapString = BitmapConverter.convertBitmapToString(bitmapObj)
//
//            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also {
//                every { it.bitmap } returns bitmapString
//                every { it.objId } returns 1
//            }
//
//            dao.insert(pixelArtCreation)
//
//            val bitmapObj2 = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
//            val bitmapString2 =  BitmapConverter.convertBitmapToString(bitmapObj2)
//
//            dao.updatePixelArtCreationBitmap(bitmapString2, 1)
//
//            val bitmapObj3 = BitmapConverter.convertStringToBitmap(dao.getAll().getOrAwaitValue().first().bitmap)
//
//            assert(bitmapObj3!!.width == bitmapObj2.width)
//            assert(bitmapObj3.height == bitmapObj2.height)
//            assert(bitmapObj3.config == bitmapObj2.config)
//        }
//    }

//    @Test
//    fun insertPixelArtCreation_assertRotationDoesReplace() {
//        runTest {
//            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also {
//                every { it.rotation } returns 180f
//                every { it.objId } returns 1
//            }
//
//            dao.insert(pixelArtCreation)
//
//            val newRotation = 90f
//            dao.updatePixelArtCreationRotation(newRotation, 1)
//
//            assert(dao.getAll().getOrAwaitValue().first().rotation == newRotation)
//        }
//    }

//    @Test
//    fun insertPixelArtCreation_assertStarredDoesReplace() {
//        runTest {
//            val pixelArtCreation = mockk<PixelArt>(relaxed = true).also {
//                every { it.starred } returns false
//                every { it.objId } returns 1
//            }
//
//            dao.insert(pixelArtCreation)
//
//            val newStarred = true
//            dao.updatePixelArtCreationStarred(newStarred, 1)
//
//            assert(dao.getAll().getOrAwaitValue().first().starred == newStarred)
//        }
//    }
}
