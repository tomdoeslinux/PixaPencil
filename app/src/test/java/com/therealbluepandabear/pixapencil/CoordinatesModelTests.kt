package com.therealbluepandabear.pixapencil

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.therealbluepandabear.pixapencil.models.Coordinates
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoordinatesModelTests {
    @Test
    fun createCoordinatesObj_assertX() {
        val coordinate = Coordinates(2,4)

        assert(coordinate.x == 2)
    }

    @Test
    fun createCoordinatesObj_assertY() {
        val coordinate = Coordinates(2,4)

        assert(coordinate.y == 4)
    }
}