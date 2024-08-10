import { rootApi } from "@/api/root-api"
import { ChakraProvider, extendTheme } from "@chakra-ui/react"
import { PropsWithChildren } from "react"
import { Provider } from "react-redux"
import store from "src/store/app-store"

const chakraTheme = extendTheme({
  fonts: {
    body: "Inter, sans-serif",
    heading: "Inter, sans-serif",
  },
})

export function AppProvider(props: PropsWithChildren) {
  return (
    <ChakraProvider theme={chakraTheme}>
      <Provider store={store}>{props.children}</Provider>
    </ChakraProvider>
  )
}
