import { rootApi } from "@/api/root-api"
import { ChakraProvider, extendTheme } from "@chakra-ui/react"
import { ApiProvider } from "@reduxjs/toolkit/query/react"
import { PropsWithChildren } from "react"

const chakraTheme = extendTheme({
  fonts: {
    body: "Inter, sans-serif",
    heading: "Inter, sans-serif",
  },
})

export function AppProvider(props: PropsWithChildren) {
  return (
    <ChakraProvider theme={chakraTheme}>
      <ApiProvider api={rootApi}>{props.children}</ApiProvider>
    </ChakraProvider>
  )
}
