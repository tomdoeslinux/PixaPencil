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

    @Test
    fun createCoordinatesObj_assertHorizontalReflectionXY() {
        val coordinate = Coordinates(1,1).getHorizontallyReflectedCoordinates(5)

        assert(coordinate.x == 1)
        assert(coordinate.y == 3)
    }

    @Test
    fun createCoordinatesObj_assertVerticalReflectionXY() {
        val coordinate = Coordinates(1,1).getVerticallyReflectedCoordinates(5)

        assert(coordinate.x == 3)
        assert(coordinate.y == 1)
    }

    @Test
    fun createCoordinatesObj_assertQuadReflectionXY() {
        val coordinates = Coordinates(1,1).getQuadReflectedCoordinateSet(5, 5)

        assert(coordinates[0].x == 1)
        assert(coordinates[0].y == 3)

        assert(coordinates[1].x == 3)
        assert(coordinates[1].y == 1)
    }
}