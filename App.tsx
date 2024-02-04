import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import {Appbar, Button, FAB, PaperProvider} from "react-native-paper";
import {AlphaType, Canvas, ColorType, Image, Skia, SkImage} from "@shopify/react-native-skia";
import {useEffect, useMemo} from "react";

interface Project {
  name: string
  coverImage: Blob
  createdAt: Date
  isStarred: boolean
}

interface ProjectCardProps {
  project: Project
}

function ProjectCard() {
  return (
    <></>
  )
}

class Bitmap {
  private static RGBA_COMPONENTS = 4
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

  setPixel(x: number, y: number): void {
    this.pixels[this.width * Bitmap.RGBA_COMPONENTS] = 255
    this.pixels[this.width * Bitmap.RGBA_COMPONENTS + 1] = 255
    this.pixels[this.width * Bitmap.RGBA_COMPONENTS + 2] = 0
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

export default function App() {
  const bitmap = useMemo(() => {
    const bitmap = new Bitmap(25, 25)
    bitmap.setPixel(0, 2)
    bitmap.setPixel(1, 2)

    return bitmap
  }, [])

  const image = useMemo(() => bitmap.getImage(), [bitmap])

  useEffect(() => {
    bitmap.setPixel(1, 1)
  }, [bitmap])

  return (
    <PaperProvider>
      <View style={styles.root}>
        <Appbar.Header style={styles.appHeader}>
          <Appbar.Content title="PixaPencil" />
          <Appbar.Action icon="calendar" onPress={() => {}} />
          <Appbar.Action icon="magnify" onPress={() => {}} />
        </Appbar.Header>

        <Canvas style={{
          width: bitmap.getWidth() * 10,
          height: bitmap.getHeight() * 10
        }}>
          <Image
            image={image}
            fit="contain"
            x={0}
            y={0}
            width={bitmap.getWidth() * 10}
            height={bitmap.getHeight() * 10}
          />
        </Canvas>

        <FAB
          icon="plus"
          style={styles.newProjectFAB}
          onPress={() => console.log('Pressed')}
        />
      </View>
    </PaperProvider>
  );
}

const styles = StyleSheet.create({
  root: {
    height: '100%',
  },
  appHeader: {
    backgroundColor: '#f0f4fc',
    elevation: 4,
  },
  newProjectFAB: {
    position: 'absolute',
    right: 0,
    bottom: 0,
    margin: 16,
  }
});
