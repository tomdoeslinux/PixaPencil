import {AlphaType, ColorType, Skia, SkImage} from "@shopify/react-native-skia";
import {Coordinate, drawLine} from "../algorithms/line";

export class Bitmap {
  private static readonly RGBA_COMPONENTS = 4
  private readonly width: number
  private readonly height: number
  private readonly pixels: Uint8Array

  constructor(width: number, height: number) {
    this.width = width
    this.height = height
    this.pixels = new Uint8Array(width * height * Bitmap.RGBA_COMPONENTS)

    for (let i = 0; i < this.pixels.length; i += Bitmap.RGBA_COMPONENTS) {
      this.pixels[i] = 0
      this.pixels[i + 1] = 0
      this.pixels[i + 2] = 0
      this.pixels[i + 3] = 255
    }
  }

  clear() {
    this.pixels.fill(0)
  }

  setPixel(x: number, y: number): void {
    const index = (y * this.width + x) * Bitmap.RGBA_COMPONENTS;

    this.pixels[index] = 255
    this.pixels[index + 1] = 255
    this.pixels[index + 2] = 0
  }

  drawLine(from: Coordinate, to: Coordinate) {
    drawLine(this, from, to)
  }

  getWidth(): number {
    return this.width
  }

  getHeight(): number {
    return this.height
  }

  getImage(): SkImage {
    const data = Skia.Data.fromBytes(this.pixels)

    const img = Skia.Image.MakeImage(
      {
        width: this.width,
        height: this.height,
        alphaType: AlphaType.Opaque,
        colorType: ColorType.RGBA_8888,
      },
      data,
      this.width * Bitmap.RGBA_COMPONENTS
    )

    return img!
  }
}
