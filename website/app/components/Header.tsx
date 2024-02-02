import { Button, Flex, IconButton, Text } from '@chakra-ui/react'
import { MdMenu } from 'react-icons/md'
import Link from 'next/link'
import React from 'react'
import AppLogo from '@/app/components/AppLogo'

interface HeaderProps {
  onMenuClicked: () => void
}

export default function Header(props: HeaderProps) {
  return (
    <Flex as='header' shadow='md' background='white' justifyContent='center' height='64px'>
      <Flex width='1280px' alignItems='center' marginLeft={{ base: '0px', md: '32px' }} marginRight='32px'>
        <Flex
          alignItems='center'
          justifyContent='center'
          width='64px'
          height='64px'
          display={{ base: 'flex', md: 'none' }}
        >
          <IconButton
            aria-label='Menu'
            icon={<MdMenu size={21} />}
            onClick={props.onMenuClicked}
            variant='ghost'
            borderRadius='999px'
          />
        </Flex>

        <Link style={{ height: '64px', display: 'flex', alignItems: 'center' }} href='/'>
          <AppLogo />
          <Text marginLeft='16px'>PixaPencil</Text>
        </Link>

        <Flex marginLeft='auto' alignItems='center'>
          <Flex gap='32px' height='64px' alignItems='center' display={{ base: 'none', md: 'flex' }}>
            <Link href='/'>Docs</Link>
            <Link href='/blog'>Blog</Link>
            <Link href='https://discord.com/invite/cYtaTnuweW'>Community</Link>
          </Flex>

          <Button
            colorScheme='messenger'
            marginLeft={{ base: 'auto', md: '32px' }}
            onClick={() => window.location.replace('https://github.com/tomdoeslinux/PixaPencil')}
          >
            Star on GitHub
          </Button>
        </Flex>
      </Flex>
    </Flex>
  )
}
