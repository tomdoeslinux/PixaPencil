package com.therealbluepandabear.pixapencil.data.local.colorpalettesdaotests

import android.graphics.Color
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.therealbluepandabear.pixapencil.converters.JsonConverter
import com.therealbluepandabear.pixapencil.dao.ColorPalettesDao
import com.therealbluepandabear.pixapencil.database.ColorPalettesDatabase
import com.therealbluepandabear.pixapencil.getOrAwaitValue
import com.therealbluepandabear.pixapencil.models.ColorPalette
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

class ColorPalettesDaoInsertionTests {
    private lateinit var database: ColorPalettesDatabase
    private lateinit var dao: ColorPalettesDao

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ColorPalettesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.colorPalettesDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun insertColorPalette_assertTitle() {
        runTest {
            val colorPalette = mockk<ColorPalette>(relaxed = true).also { every { it.colorPaletteName } returns "ColorPalette1" }

            dao.insertColorPalette(colorPalette)

            assert(dao.getAllColorPalettes().getOrAwaitValue().first().colorPaletteName == colorPalette.colorPaletteName)
        }
    }

    @Test
    fun insertColorPalette_assertColorPaletteColorData() {
        runTest {
            val colorPaletteData: List<Int> = listOf(
                Color.BLACK,
                Color.YELLOW,
                Color.GREEN
            )
            val colorPaletteDataStr = JsonConverter.convertListToJsonString(colorPaletteData)

            val colorPalette = mockk<ColorPalette>(relaxed = true).also { every { it.colorPaletteColorData } returns colorPaletteDataStr }

            dao.insertColorPalette(colorPalette)

            assert(dao.getAllColorPalettes().getOrAwaitValue().first().colorPaletteColorData == colorPaletteDataStr)
        }
    }

    @Test
    fun insertColorPalette_assertObjId() {
        runTest {
            val colorPalette = mockk<ColorPalette>(relaxed = true).also { every { it.objId } returns 1 }

            dao.insertColorPalette(colorPalette)

            assert(dao.getAllColorPalettes().getOrAwaitValue().first().objId == colorPalette.objId)
        }
    }

    @Test
    fun insertColorPalette_assertIsPrimaryColorPalette() {
        runTest {
            val colorPalette = mockk<ColorPalette>(relaxed = true).also { every { it.isPrimaryColorPalette } returns true }

            dao.insertColorPalette(colorPalette)

            assert(dao.getAllColorPalettes().getOrAwaitValue().first().isPrimaryColorPalette == colorPalette.isPrimaryColorPalette)
        }
    }
}