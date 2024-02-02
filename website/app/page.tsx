import { Box, Button, Text, Flex, Image, Heading } from '@chakra-ui/react'

interface GetOnButtonProps {
  label: string
  iconSrc: string
}

function GetOnButton(props: GetOnButtonProps) {
  return (
    <Button
      border='1px solid gray'
      variant='ghost'
      fontWeight='normal'
      textAlign='left'
      height={{ base: '50px', lg: '70px' }}
      gap='8px'
    >
      <Image src={props.iconSrc} height={{ base: '25px', lg: '40px' }} />
      <Box>
        <Text fontSize={{ base: '12px', lg: '16px' }}>Get on</Text>
        <Text fontWeight='bold' fontSize={{ base: 'md', lg: '2xl' }}>
          {props.label}
        </Text>
      </Box>
    </Button>
  )
}

function LandingPage() {
  return (
    <Flex
      as='main'
      flexGrow={1}
      justifyContent='center'
      alignItems='center'
      margin='32px'
      sx={{
        '@media screen and (max-width: 550px)': {
          margin: '16px',
        },
      }}
    >
      <Flex width='1280px' alignItems='center' flexDirection={{ base: 'column', lg: 'row' }}>
        <Flex maxWidth='531px' flexDirection='column'>
          <Heading
            marginTop={{ base: '54px', lg: undefined }}
            as='h1'
            size='3xl'
            fontSize={{ base: '40px', lg: '60px' }}
            sx={{
              '@media screen and (max-width: 550px)': {
                fontSize: '34px',
              },
            }}
            textAlign={{ base: 'center', lg: 'left' }}
            lineHeight={{ base: undefined, lg: '65px' }}
          >
            Level up your Pixel Art
          </Heading>

          <Text marginTop='8px' textAlign={{ base: 'center', lg: 'left' }}>
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
            industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and
            scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into
            electronic typesetting, remaining essent
          </Text>

          <Flex
            gap={{ base: '8px', lg: '14px' }}
            marginTop={{ base: '24px', lg: '32px' }}
            justifyContent={{ base: 'center', lg: 'flex-start' }}
          >
            <GetOnButton label='F-Droid' iconSrc='/fdroid.svg' />
            <GetOnButton label='Google Play' iconSrc='/google.svg' />
          </Flex>
        </Flex>

        <Flex
          flexGrow={1}
          alignItems='center'
          justifyContent='center'
          flexShrink='0'
          marginTop={{ base: '32px', lg: '0px' }}
        >
          <Image src='/pixapencil-app-image-1.jpg' height='700px' minWidth='initial' borderRadius='5px' />
        </Flex>
      </Flex>
    </Flex>
  )
}

export default function Home() {
  return <LandingPage />
}
