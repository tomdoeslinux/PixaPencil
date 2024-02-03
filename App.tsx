import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import {Appbar, Button, FAB, PaperProvider} from "react-native-paper";

export default function App() {
  return (
    <PaperProvider>
      <View style={styles.root}>
        <Appbar.Header style={styles.appHeader}>
          <Appbar.Content title="PixaPencil" />
          <Appbar.Action icon="calendar" onPress={() => {}} />
          <Appbar.Action icon="magnify" onPress={() => {}} />
        </Appbar.Header>

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
