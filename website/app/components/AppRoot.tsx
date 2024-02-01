'use client'

import React, { PropsWithChildren, useEffect, useState } from 'react'
import { Box, ChakraProvider, Flex } from '@chakra-ui/react'
import { theme } from '@/app/theme'
import Sidebar from '@/app/components/Sidebar'
import Header from '@/app/components/Header'
import './AppRoot.css'

export default function AppRoot(props: PropsWithChildren) {
  const [isMenuOpen, setIsMenuOpen] = useState(false)

  useEffect(() => {
    const element = document.querySelector('.hamburger-bg-overlay')

    if (isMenuOpen) {
      element?.classList.add('shown')
      element?.classList.remove('hiding')
      document.body.style.overflowY = 'hidden'
    } else {
      element?.classList.add('hiding')
      document.body.style.overflowY = 'auto'

      setTimeout(() => {
        element?.classList.remove('shown')
      }, 300)
    }
  }, [isMenuOpen])

  return (
    <ChakraProvider theme={theme}>
      <Flex flexDirection='column' height='100vh' /*overflow="clip"*/>
        <Box
          display={{ base: 'block', md: 'none' }}
          transition='transform 0.3s ease-in-out'
          transform={!isMenuOpen ? 'translateX(-280px)' : 'translateX(0px)'}
          zIndex={999}
        >
          <Sidebar onMenuItemClick={() => setIsMenuOpen(false)} />
        </Box>
        <Header onMenuClicked={() => setIsMenuOpen(true)} />
        {props.children}
        <Box onClick={() => setIsMenuOpen(false)} className='hamburger-bg-overlay' />
      </Flex>
    </ChakraProvider>
  )
}
