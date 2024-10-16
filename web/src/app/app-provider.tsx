import { ChakraProvider, extendTheme, ThemeOverride } from "@chakra-ui/react"
import { PropsWithChildren } from "react"
import { Provider } from "react-redux"
import store from "src/store/app-store"

const chakraTheme = extendTheme({
  fonts: {
    body: "Manrope, sans-serif",
    heading: "Manrope, sans-serif",
  },
  colors: {
    px: {
      blue: {
        med: "#3660D3",
        dark: "#2548A7",
        darkest: "#1D3986",
      },
      gray: "#CFCFCF",
    },
  },
  components: {
    Heading: {
      baseStyle: {
        color: "#242124",
        fontWeight: "medium",
      },
    },
    Text: {
      baseStyle: {
        color: "#242124",
        fontWeight: "medium",
      },
    },
    Button: {
      baseStyle: {
        fontWeight: "medium",
      },
    },
  },
  global: {
    body: {
      color: "#242124",
      fontWeight: "medium",
    },
    p: {
      color: "#242124",
      fontWeight: "medium",
    },
  },
} satisfies ThemeOverride)

export function AppProvider(props: PropsWithChildren) {
  return (
    <ChakraProvider theme={chakraTheme}>
      <Provider store={store}>{props.children}</Provider>
    </ChakraProvider>
  )
}
