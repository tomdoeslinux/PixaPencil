import {Bitmap} from "../bitmap/bitmap";

export interface Coordinate {
  x: number
  y: number
}

function drawLineY(callback: (coords: { x: number, y: number }) => void, from: Coordinate, to: Coordinate) {
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
    callback({ x, y })
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

function drawLineX(callback: (coords: { x: number, y: number }) => void, from: Coordinate, to: Coordinate) {
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
    callback({ x, y })
    y++

    if (p < 0) {
      p += 2 * differenceX
    } else {
      p = p + 2 * differenceX - 2 * differenceY
      x += xi
    }
  }
}

function drawLine(callback: (coords: { x: number, y: number }) => void, from: Coordinate, to: Coordinate) {
  const x = from.x
  const y = from.y

  const differenceX = to.x - x
  const differenceY = to.y - y

  if (differenceY <= differenceX) {
    if (Math.abs(differenceY) > differenceX) {
      drawLineX(callback, to, from)
    } else {
      drawLineY(callback, from, to)
    }
  } else {
    if (Math.abs(differenceX) > differenceY) {
      drawLineY(callback, to, from)
    } else {
      drawLineX(callback, from, to)
    }
  }
}
