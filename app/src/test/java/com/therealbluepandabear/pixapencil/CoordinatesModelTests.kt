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

    @Test
    fun createCoordinatesObj_assertXY() {
        val coordinate = Coordinates(10,20)

        assert(coordinate.x == 10)
        assert(coordinate.y == 20)
    }

    @Test
    fun createCoordinatesObj_thenConvertToCoordinatesDouble_assertX() {
        val coordinate = Coordinates(5,10).convertToCoordinatesDouble()

        assert(coordinate.x == 5.0)
    }

    @Test
    fun createCoordinatesObj_thenConvertToCoordinatesDouble_assertY() {
        val coordinate = Coordinates(5,10).convertToCoordinatesDouble()

        assert(coordinate.y == 10.0)
    }

    @Test
    fun createCoordinatesObj_thenConvertToCoordinatesDouble_assertXY() {
        val coordinate = Coordinates(5,10).convertToCoordinatesDouble()

        assert(coordinate.x == 5.0)
        assert(coordinate.y == 10.0)
    }
}