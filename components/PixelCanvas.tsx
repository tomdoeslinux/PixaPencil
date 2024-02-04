import {useMemo, useRef, useState} from "react";
import {Canvas, createPicture, Image, PointMode, Skia, SkImage, useCanvasRef} from "@shopify/react-native-skia";
import {GestureResponderEvent, StyleSheet} from "react-native";
import {Bitmap} from "../foundation/bitmap/bitmap";

export default function PixelCanvas() {
  const bitmapRef = useRef(new Bitmap(500, 500))
  const [image, setImage] = useState<SkImage | null>(bitmapRef.current.getImage())
  const prevX = useRef<number | null>(null);
  const prevY = useRef<number | null>(null);
  const canvasRef = useCanvasRef()

  const imageWidth = useMemo(() => bitmapRef.current.getWidth(), [])
  const imageHeight = useMemo(() => bitmapRef.current.getHeight(), [])

  const scaleX =  useMemo(() => imageWidth / bitmapRef.current.getWidth(), [])
  const scaleY =  useMemo(() => imageHeight / bitmapRef.current.getHeight(), [])

  createPicture((c) => {
    const paint = Skia.Paint();
    paint.setColor(Skia.Color("cyan"))
    c.drawPoints(PointMode.Points, [{ x: 0, y: 0 }], paint)
  })

  function touchHandler(e: GestureResponderEvent) {
    const bitmap = bitmapRef.current
    const x = Math.floor(e.nativeEvent.locationX / scaleX)
    const y = Math.floor(e.nativeEvent.locationY / scaleY)

    bitmap.setPixel(x, y)
    setImage(bitmap.getImage())

    prevX.current = x
    prevY.current = y

    bitmapRef.current.clear()
    bitmapRef.current.drawLine({ x: 0, y: 0 }, { x: prevX.current ?? 0, y: prevY.current ?? 0 })
    setImage(bitmapRef.current.getImage())
  }

  // useEffect(() => {
  //   bitmapRef.current.clear()
  //   bitmapRef.current.drawLine({ x: 0, y: 0 }, { x: prevX ?? 0, y: prevY ?? 0 })
  //   setImage(bitmapRef.current.getImage())
  // }, [prevX, prevY])

  return (
    <Canvas
      style={styles.canvas}
      onTouchMove={touchHandler}
      onTouchStart={touchHandler}
      onTouchEnd={touchHandler}
    >
      <Image
        image={image}
        fit="contain"
        x={0}
        y={0}
        width={imageWidth}
        height={imageHeight}
      />
    </Canvas>
  )
}

const styles = StyleSheet.create({
  canvas: {
    flexGrow: 1,
    backgroundColor: 'blue',
  },
})
