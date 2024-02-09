import {Coordinate} from "../types/coordinate";
import {drawLine} from "../algorithms/line";

export class Bitmap {
  private readonly pixels: Uint8Array
  private readonly size: number

  pixelsDrawn: Coordinate[] = []

  constructor(size: number) {
    this.size = size
    this.pixels = new Uint8Array(size * size * 4)
    this.pixels.fill(255)
  }

  setPixel(coordinate: Coordinate) {
    if (coordinate.x >= this.size || coordinate.y >= this.size || coordinate.x < 0 || coordinate.y < 0) {
      return
    }

    const index = (coordinate.y * this.size + coordinate.x) * 4
    this.pixels[index] = 0
    this.pixels[index + 1] = 0
    this.pixels[index + 2] = 0

    this.pixelsDrawn.push(coordinate)
  }

  drawLine(from: Coordinate, to: Coordinate) {
    drawLine(this, from, to)
  }

  getPixels(): Uint8Array {
    'worklet'
    return this.pixels
  }

  getSize(): number {
    return this.size
  }
}
