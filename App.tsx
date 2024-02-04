import { StatusBar } from 'expo-status-bar';
import {GestureResponderEvent, StyleSheet, Text, View} from 'react-native';
import {Appbar, Button, FAB, PaperProvider} from "react-native-paper";
import {AlphaType, Canvas, ColorType, Image, Skia, SkImage, useCanvasRef} from "@shopify/react-native-skia";
import {useEffect, useMemo, useState} from "react";
import {blue50} from "react-native-paper/lib/typescript/styles/themes/v2/colors";
import PixelCanvas from "./components/PixelCanvas";

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

export default function App() {
  return (
    <PaperProvider>
      <View style={styles.root}>
        <PixelCanvas />

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
