import {GestureResponderEvent, StyleSheet, Text, View} from 'react-native';
import PixelCanvas from "./components/PixelCanvas";
import {SafeAreaProvider} from "react-native-safe-area-context";
import ColorPicker from "./components/ColorPicker";

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
      <SafeAreaProvider style={styles.root}>
        <View style={{ marginTop: 60 }}>
          <ColorPicker />
        </View>
      </SafeAreaProvider>
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
});
