import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import { ChakraProvider, extendTheme } from '@chakra-ui/react'
import { ApiProvider } from '@reduxjs/toolkit/query/react'
import { privateApi } from './store/api/privateApi.ts'

const chakraTheme = extendTheme({
  fonts: {
      body: 'Inter, sans-serif',
      heading: 'Inter, sans-serif'
  },
})

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ChakraProvider theme={chakraTheme}>
      <ApiProvider api={privateApi}>
        <App />
      </ApiProvider>
    </ChakraProvider>
  </React.StrictMode>,
)
