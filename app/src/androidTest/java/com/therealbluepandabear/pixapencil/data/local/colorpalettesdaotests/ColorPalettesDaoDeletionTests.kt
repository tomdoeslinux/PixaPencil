package com.therealbluepandabear.pixapencil.data.local.colorpalettesdaotests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
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

/**
 * Test completion summary for `ColorPalettesDaoDeletionTests`:
 *
 * **Last completion of tests in this package file:**
 *
 * - 2022-05-02 21:04 (12/12 passed) on API 32
 *
 * **Last completion of tests in this package file for API 32:**
 * - 2022-05-02 21:04 (12/12 passed)
 */

class ColorPalettesDaoDeletionTests {
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
    fun insert5ColorPalettes_deleteItems_assertSizeChanges() {
        runTest {
            for (i in 1..5) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            dao.deleteColorPalette(1)
            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 4)

            dao.deleteColorPalette(2)
            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 3)

            dao.deleteColorPalette(3)
            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 2)

            dao.deleteColorPalette(4)
            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 1)

            dao.deleteColorPalette(5)
            assert(dao.getAllColorPalettes().getOrAwaitValue().isEmpty())
        }
    }

    @Test
    fun insert16ColorPalettes_deleteHalf_assertSize8() {
        runTest {
            for (i in 1..16) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..8) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 8)
        }
    }

    @Test
    fun insert32ColorPalettes_deleteHalf_assertSize16() {
        runTest {
            for (i in 1..32) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..16) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 16)
        }
    }

    @Test
    fun insert64ColorPalettes_deleteHalf_assertSize32() {
        runTest {
            for (i in 1..64) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..32) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 32)
        }
    }

    @Test
    fun insert128ColorPalettes_deleteHalf_assertSize64() {
        runTest {
            for (i in 1..128) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..64) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 64)
        }
    }

    @Test
    fun insert256PColorPalettes_deleteHalf_assertSize128() {
        runTest {
            for (i in 1..256) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..128) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 128)
        }
    }

    @Test
    fun insert512ColorPalettes_deleteHalf_assertSize256() {
        runTest {
            for (i in 1..512) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..256) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 256)
        }
    }

    @Test
    fun insert1024ColorPalettes_deleteHalf_assertSize512() {
        runTest {
            for (i in 1..1024) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..512) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 512)
        }
    }

    @Test
    fun insert2048ColorPalettes_deleteHalf_assertSize1024() {
        runTest {
            for (i in 1..2048) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..1024) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 1024)
        }
    }

    @Test
    fun insert4096ColorPalettes_deleteHalf_assertSize2048() {
        runTest {
            for (i in 1..4096) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..2048) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 2048)
        }
    }

    @Test
    fun insert8192ColorPalettes_deleteHalf_assertSize4096() {
        runTest {
            for (i in 1..8192) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..4096) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 4096)
        }
    }

    @Test
    fun insert16384ColorPalettes_deleteHalf_assertSize8192() {
        runTest {
            for (i in 1..16384) {
                val colorPalette = mockk<ColorPalette>(relaxed = true)
                every { colorPalette.objId } returns i

                dao.insertColorPalette(colorPalette)
            }

            for (i in 1..8192) {
                dao.deleteColorPalette(i)
            }

            assert(dao.getAllColorPalettes().getOrAwaitValue().size == 8192)
        }
    }
}
