import {Button, GestureResponderEvent, PixelRatio, StyleSheet, useWindowDimensions, View} from "react-native";
import {useSafeAreaInsets, SafeAreaView} from "react-native-safe-area-context";
import {ExpoWebGLRenderingContext, GLView} from "expo-gl";
import {WebGLUtils} from "../foundation/webgl/WebGLUtils";
import {useRef} from "react";

interface Coordinate {
  x: number
  y: number
}

class Bitmap {
  private readonly gl: ExpoWebGLRenderingContext
  private readonly width: number
  private readonly height: number
  private readonly canvasWidth: number
  private readonly canvasHeight: number
  private readonly pixels: Uint8Array
  private program: WebGLProgram
  private texture: WebGLTexture;

  private static readonly RGBA_COMPONENTS = 4

  constructor(
    gl: ExpoWebGLRenderingContext,
    width: number,
    height: number,
    canvasWidth: number,
    canvasHeight: number
  ) {
    this.gl = gl

    this.width = width
    this.height = height
    this.canvasWidth = canvasWidth
    this.canvasHeight = canvasHeight
    this.pixels = new Uint8Array(this.width * this.height * Bitmap.RGBA_COMPONENTS)
    this.pixels.fill(255)

    this.initProgram().then(() => {
      this.initTexture()
      this.initUniforms()
      this.initAttributes()

      WebGLUtils.prepareViewport(this.gl)
      this.gl.endFrameEXP()
    })
  }

  private async initProgram() {
    this.program = await WebGLUtils.createProgramFromScripts(
      this.gl,
      require('../foundation/shaders/bitmap_vs.glsl'),
      require('../foundation/shaders/bitmap_fs.glsl')
    )
    this.gl.useProgram(this.program)
  }

  public initTexture() {
    this.texture = this.gl.createTexture()
    this.gl.activeTexture(this.gl.TEXTURE0)
    this.gl.bindTexture(this.gl.TEXTURE_2D, this.texture)
    this.gl.texImage2D (
      this.gl.TEXTURE_2D,
      0,
      this.gl.RGBA,
      this.width,
      this.height,
      0,
      this.gl.RGBA,
      this.gl.UNSIGNED_BYTE,
      this.pixels
    )

    this.gl.texParameteri(this.gl.TEXTURE_2D, this.gl.TEXTURE_WRAP_S, this.gl.CLAMP_TO_EDGE)
    this.gl.texParameteri(this.gl.TEXTURE_2D, this.gl.TEXTURE_WRAP_T, this.gl.CLAMP_TO_EDGE)
    this.gl.texParameteri(this.gl.TEXTURE_2D, this.gl.TEXTURE_MIN_FILTER, this.gl.NEAREST)
    this.gl.texParameteri(this.gl.TEXTURE_2D, this.gl.TEXTURE_MAG_FILTER, this.gl.NEAREST)
  }

  public initUniforms() {
    const uSampler = this.gl.getUniformLocation(this.program, "u_sampler")
    this.gl.uniform1i(uSampler, 0)

    const uResolution = this.gl.getUniformLocation(this.program, "u_resolution")
    this.gl.uniform2f(uResolution, this.canvasWidth, this.canvasHeight)
  }

  public initAttributes() {
    const vertices = [
      -this.canvasWidth, -this.canvasWidth,
      this.canvasWidth, -this.canvasWidth,
      -this.canvasWidth,  this.canvasWidth,
      this.canvasWidth,  this.canvasWidth,
    ]

    const textCoords = new Float32Array([
      0.0, 0.0,
      1.0, 0.0,
      0.0, 1.0,
      1.0, 1.0,
    ])

    const vertexBuffer = this.gl.createBuffer()
    this.gl.bindBuffer(this.gl.ARRAY_BUFFER, vertexBuffer)
    this.gl.bufferData(this.gl.ARRAY_BUFFER, new Float32Array(vertices), this.gl.STATIC_DRAW)

    const positionLocation = this.gl.getAttribLocation(this.program, 'position')

    this.gl.enableVertexAttribArray(positionLocation)
    this.gl.vertexAttribPointer(positionLocation, 2, this.gl.FLOAT, false, 0, 0)
  }

  public setPixel(coordinate: Coordinate) {
    const index = (coordinate.y * this.width + coordinate.x) * Bitmap.RGBA_COMPONENTS;

    this.pixels[index] = 255
    this.pixels[index + 1] = 0
    this.pixels[index + 2] = 44

    this.draw()
  }

  private draw() {
    this.gl.bindTexture(this.gl.TEXTURE_2D, this.texture)
    this.gl.texImage2D (
      this.gl.TEXTURE_2D,
      0,
      this.gl.RGBA,
      this.width,
      this.height,
      0,
      this.gl.RGBA,
      this.gl.UNSIGNED_BYTE,
      this.pixels
    )

    this.gl.drawArrays(this.gl.TRIANGLE_STRIP, 0, 4)
    this.gl.endFrameEXP()
  }

  public getHeight() {
    return this.height
  }

  public getWidth() {
    return this.width
  }
}

class BitmapUtils {
  static convertScreenCoordToBitmapCoord(
    screenCoord: Coordinate,
    bitmapWidth: number,
    bitmapHeight: number,
    canvasWidth: number,
    canvasHeight: number
  ): Coordinate {
    const scaleX = canvasWidth / bitmapWidth
    const scaleY = canvasHeight / bitmapHeight

    return {
      x: Math.floor(screenCoord.x / scaleX),
      y: Math.floor(screenCoord.y / scaleY),
    }
  }
}

export default function PixelCanvas() {
  const { width, height } = useWindowDimensions()
  const insets = useSafeAreaInsets()
  const bitmapRef = useRef<Bitmap | null>()

  function touchHandler(e: GestureResponderEvent) {
    if (bitmapRef.current) {
      const bitmapCoord = BitmapUtils.convertScreenCoordToBitmapCoord(
        {
          x: e.nativeEvent.pageX,
          y: e.nativeEvent.pageY - insets.top,
        },
        bitmapRef.current!.getWidth(),
        bitmapRef.current!.getWidth(),
        width,
        width,
      )

      bitmapRef.current!.setPixel(bitmapCoord)
    }
  }

  return (
    <SafeAreaView>
      <GLView
        style={{
          width: width,
          height: height
      }}
        onTouchMove={touchHandler}
        onTouchStart={touchHandler}
        onTouchEnd={touchHandler}
        onContextCreate={(gl) => {
          bitmapRef.current = new Bitmap(gl, 100, 100, width, height)
        }}
      />
    </SafeAreaView>
  )
}

const styles = StyleSheet.create({
  canvas: {
    flexGrow: 1,
    backgroundColor: 'blue',
  },
})
