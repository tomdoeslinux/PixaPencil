import { Button, Flex, ListItem, UnorderedList } from '@chakra-ui/react'
import Link from 'next/link'
import React, { PropsWithChildren } from 'react'
import { usePathname } from 'next/navigation'

interface MenuButtonProps extends PropsWithChildren {
  href: string
  isSelected: boolean
  onClick: () => void
}

function MenuButton(props: MenuButtonProps) {
  return (
    <ListItem
      fontWeight='normal'
      height='56px'
      onClick={props.onClick}
      background={props.isSelected ? 'gray.100' : 'white'}
      display='flex'
      alignItems='center'
    >
      <Link
        href={props.href}
        style={{
          width: '100%',
          height: '56px',
          paddingLeft: '20px',
          display: 'flex',
          alignItems: 'center',
        }}
      >
        {props.children}
      </Link>
    </ListItem>
  )
}

interface SidebarProps {
  onMenuItemClick: () => void
}

export default function Sidebar(props: SidebarProps) {
  const pathname = usePathname()

  const menuItems = [
    { href: '/', label: 'Home' },
    { href: '/docs', label: 'Docs' },
    { href: '/blog', label: 'Blog' },
    { href: 'https://discord.com/invite/cYtaTnuweW', label: 'Community' },
  ]

  return (
    <Flex
      display={{ base: 'flex', md: 'none' }}
      minWidth='280px'
      backgroundColor='white'
      height='100vh'
      position='absolute'
      flexDirection='column'
      as='nav'
    >
      <UnorderedList styleType='none' margin='0px'>
        {menuItems.map((item) => (
          <MenuButton
            key={item.href}
            href={item.href}
            isSelected={pathname === item.href}
            onClick={props.onMenuItemClick}
          >
            {item.label}
          </MenuButton>
        ))}
      </UnorderedList>
    </Flex>
  )
}
