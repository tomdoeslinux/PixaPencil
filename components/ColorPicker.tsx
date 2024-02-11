import {Pressable, ScrollView, StyleSheet, Text} from "react-native";
import {View} from 'react-native'
import { Feather } from '@expo/vector-icons';
import {useRef, useState} from "react";

interface ColorPickerProps {
  onSelectColor: (color: string) => void
}

export default function ColorPicker(props: ColorPickerProps) {
  const scrollViewRef = useRef<ScrollView | null>(null)
  const [colors, setColors] = useState<string[]>([
    '#000000',
    '#444455',
    '#220077',
    '#3322ff',
    '#660033',
    '#ff0033',
    '#770088',
    '#ff33dd',
    '#007722',
    '#00ff33',
    '#227788',
    '#22eeff',
    '#996600',
    '#ffee33',
    '#777799',
    '#ffffff',
  ])

  return (
    <ScrollView
      ref={scrollViewRef}
      horizontal={true}
      showsHorizontalScrollIndicator={false}
      onContentSizeChange={() => scrollViewRef.current!.scrollToEnd({ animated: false })}
      styles={styles.root}
    >
      {colors.map((color, index) => (
        <Pressable
          onLongPress={() => setColors((prevColors) => prevColors.filter((_color) => _color !== color))}
          onPress={() => props.onSelectColor(color)}
          style={{...styles.color, backgroundColor: color }} key={index}
        />
      ))}
      <Pressable onPress={() => {
        setColors(prevColors => [...prevColors, '#899843'])
      }} style={styles.addColor}>
        <Feather name="plus" size={40} color="white" />
      </Pressable>
    </ScrollView>
  )
}

const styles = StyleSheet.create({
  root: {
    position: 'absolute',
  },
  color: {
    height: 50,
    width: 50,
  },
  addColor: {
    height: 50,
    width: 50,
    backgroundColor: 'gray',
    alignItems: 'center',
    justifyContent: 'center',
  }
})
