import { Button, Flex } from '@chakra-ui/react'
import Link from 'next/link'
import React, { PropsWithChildren } from 'react'

function MenuButton(props: PropsWithChildren & { onClick: () => void }) {
  return (
    <Button
      width='100%'
      justifyContent='flex-start'
      variant='ghost'
      fontWeight='normal'
      height='56px'
      paddingLeft='20px'
      onClick={props.onClick}
    >
      {props.children}
    </Button>
  )
}

interface SidebarProps {
  onMenuItemClick: () => void
}

export default function Sidebar(props: SidebarProps) {
  return (
    <Flex
      display={{ base: 'flex', md: 'none' }}
      minWidth='280px'
      backgroundColor='white'
      height='100vh'
      position='absolute'
      flexDirection='column'
    >
      <Link href='/'>
        <MenuButton onClick={props.onMenuItemClick}>Docs</MenuButton>
      </Link>
      <Link href='/blog'>
        <MenuButton onClick={props.onMenuItemClick}>Blog</MenuButton>
      </Link>
      <Link href='/'>
        <MenuButton onClick={props.onMenuItemClick}>Community</MenuButton>
      </Link>
    </Flex>
  )
}
