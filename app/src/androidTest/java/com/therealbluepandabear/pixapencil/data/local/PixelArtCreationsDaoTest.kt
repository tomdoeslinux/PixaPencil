package com.therealbluepandabear.pixapencil.data.local

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
}
