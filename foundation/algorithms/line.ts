import {Bitmap} from "../bitmap/bitmap";

export interface Coordinate {
  x: number
  y: number
}

function drawLineY(bitmap: Bitmap, from: Coordinate, to: Coordinate) {
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
    bitmap.setPixel(x, y)
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

function drawLineX(bitmap: Bitmap, from: Coordinate, to: Coordinate) {
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
    bitmap.setPixel(x, y)
    y++

    if (p < 0) {
      p += 2 * differenceX
    } else {
      p = p + 2 * differenceX - 2 * differenceY
      x += xi
    }
  }
}

export function drawLine(bitmap: Bitmap, from: Coordinate, to: Coordinate) {
  const x = from.x
  const y = from.y

  const differenceX = to.x - x
  const differenceY = to.y - y

  if (differenceY <= differenceX) {
    if (Math.abs(differenceY) > differenceX) {
      drawLineX(bitmap, to, from)
    } else {
      drawLineY(bitmap, from, to)
    }
  } else {
    if (Math.abs(differenceX) > differenceY) {
      drawLineY(bitmap, to, from)
    } else {
      drawLineX(bitmap, from, to)
    }
  }
}
