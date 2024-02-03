import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import {Appbar, Button, PaperProvider} from "react-native-paper";

export default function App() {
  return (
    <PaperProvider>
      <Appbar.Header style={styles.appHeader}>
        <Appbar.Content title="PixaPencil" />
        <Appbar.Action icon="calendar" onPress={() => {}} />
        <Appbar.Action icon="magnify" onPress={() => {}} />
      </Appbar.Header>
    </PaperProvider>
  );
}

const styles = StyleSheet.create({
  appHeader: {
    backgroundColor: '#f0f4fc',
    elevation: 4,
  },
});
