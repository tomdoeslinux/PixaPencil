import {useEffect, useMemo, useRef, useState} from "react";
import {
  AlphaType,
  Canvas, ColorType,
  createPicture,
  Image,
  notifyChange,
  PointMode,
  Skia,
  SkImage,
  useCanvasRef
} from "@shopify/react-native-skia";
import {StyleSheet, View, useWindowDimensions, GestureResponderEvent} from "react-native";
import Animated, {runOnJS, useAnimatedReaction, useDerivedValue, useSharedValue} from "react-native-reanimated";
import {Button} from "react-native-paper";
import {Gesture, GestureDetector, GestureHandlerRootView} from "react-native-gesture-handler";

const size = 50

function newArr(): Uint8Array {
  const arr = new Uint8Array(size * size * 4)
  arr.fill(255)

  return arr
}

export interface Coordinate {
  x: number
  y: number
}

function setPixel(pixels: Uint8Array, x: number, y: number) {
  'worklet'

  if (x >= size || y >= size || x < 0 || y < 0) {
    return
  }

  const index = (y * size + x) * 4;

  pixels[index] = 0
  pixels[index + 1] = 0
  pixels[index + 2] = 0
}

function drawLineY(pixels: Uint8Array, from: Coordinate, to: Coordinate) {
  'worklet'
  let x = from.x
  let y = from.y

  const differenceX = to.x - x
  let differenceY = to.y - y

  let yi = 1
  const xi = 1

  if (differenceY < 0) {
    differenceY = -differenceY
    yi = -1
  }

  let p = 2 * differenceY - differenceX

  while (x <= to.x) {
    setPixel(pixels, x, y)
    x++

    if (p < 0) {
      p += 2 * differenceY
      if (differenceY > differenceX) {
        x += xi
      }
    } else {
      p = p + 2 * differenceY - 2 * differenceX
      y += yi
    }
  }
}

function drawLineX(pixels: Uint8Array, from: Coordinate, to: Coordinate) {
  'worklet'
  let x = from.x
  let y = from.y

  let differenceX = to.x - x
  const differenceY = to.y - y

  let xi = 1

  if (differenceX <= 0) {
    differenceX = -differenceX
    xi = -1
  }

  let p = 2 * differenceX - differenceY

  while (y <= to.y) {
    setPixel(pixels, x, y)
    y++

    if (p < 0) {
      p += 2 * differenceX
    } else {
      p = p + 2 * differenceX - 2 * differenceY
      x += xi
    }
  }
}

function drawLine(pixels: Uint8Array, from: Coordinate, to: Coordinate) {
  'worklet'
  const x = from.x
  const y = from.y

  const differenceX = to.x - x
  const differenceY = to.y - y

  if (differenceY <= differenceX) {
    if (Math.abs(differenceY) > differenceX) {
      drawLineX(pixels, to, from)
    } else {
      drawLineY(pixels, from, to)
    }
  } else {
    if (Math.abs(differenceX) > differenceY) {
      drawLineY(pixels, to, from)
    } else {
      drawLineX(pixels, from, to)
    }
  }
}

export default function PixelCanvas() {
  const pixels = useSharedValue(newArr());
  const coordinateTapped = useSharedValue<{ x: number, y: number } | null>(null)
  const prevCoordinateTapped = useSharedValue<{ x: number, y: number } | null>(null)

  const CANVAS_SIZE = 1000;

  const img = useDerivedValue(() => {
    // If we are not on the UI thread, don't do anything.
    if (!_WORKLET) {
      return
    }

    if (coordinateTapped.value) {
      setPixel(pixels.value, coordinateTapped.value!.x, coordinateTapped.value!.y)

      if (prevCoordinateTapped.value) {
        drawLine(pixels.value, prevCoordinateTapped.value!, coordinateTapped.value!)
      }

      prevCoordinateTapped.value = coordinateTapped.value
    }

    const data = Skia.Data.fromBytes(pixels.value)

    return Skia.Image.MakeImage(
      {
        width: size,
        height: size,
        alphaType: AlphaType.Opaque,
        colorType: ColorType.RGBA_8888,
      },
      data,
      size * 4
    )!;
  }, [coordinateTapped]);

  function touchHandler(e: GestureResponderEvent) {
    const x = Math.floor(e.nativeEvent.locationX / (300 / size))
    const y = Math.floor(e.nativeEvent.locationY / (300 / size))

    coordinateTapped.value = { x, y }
  }

  return (
    <GestureHandlerRootView
      onTouchMove={touchHandler}
      onTouchStart={touchHandler}
      onTouchEnd={() => coordinateTapped.value = null}
    >
      <Canvas
        style={{ width: CANVAS_SIZE, height: CANVAS_SIZE }}
      >
        <Image image={img} x={0} y={0} width={300} height={300} fit="cover" />
      </Canvas>
    </GestureHandlerRootView>
  );
}

const styles = StyleSheet.create({
  canvas: {
    flexGrow: 1,
    backgroundColor: 'blue',
  },
})
