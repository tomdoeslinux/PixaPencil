import { useEffect, useState } from 'react'
import './App.css'
import { Box, Flex, Grid, IconButton, Image, Text } from '@chakra-ui/react'
import { Creation } from 'models'
import { MdFavorite, MdFavoriteBorder } from 'react-icons/md'
import { useGetCreationsQuery } from './store/creation/creation'

interface LikeButtonProps {
  onLike: () => void
  onUnlike: () => void 
}

function LikeButton(props: LikeButtonProps) {
  const [isLiked, setIsLiked] = useState(false)

  useEffect(() => {
    if (isLiked) {
      props.onLike() 
    } else {
      props.onUnlike()
    }
  }, [isLiked])

  return (
    <IconButton 
      marginLeft="auto"
      variant="ghost" 
      icon={isLiked ? <MdFavorite /> : <MdFavoriteBorder />} 
      aria-label="Like" 
      onClick={() => setIsLiked((prevIsLiked) => !prevIsLiked)}
    />
  )
}

interface CreationCardProps {
  creation: Creation
}

{/* <Flex alignItems="center" paddingLeft="16px" height="50px">
{props.creation.title}
<LikeButton onLike={() => {}} onUnlike={() => {}} />
</Flex> */}

function CreationCard(props: CreationCardProps) {
  return (
    <Flex 
      flexDirection="column" 
      borderRadius="xl" 
      overflow="clip" 
      shadow="md"
      width="100%"
      cursor="pointer"
      position="relative"
    >
      <Image 
        src={props.creation.coverImageUrl} 
        objectFit="cover" 
        aspectRatio="1/1" 
        width="100%" 
      />

      <Flex 
        position="absolute" 
        width="100%" 
        height="100%" 
        background="linear-gradient(to bottom, rgba(0, 0, 0, 0) 30%, rgba(0, 0, 0, 0.8) 100%)" 
        opacity="0"
        transition="opacity 0.1s"
        _hover={{ opacity: 1 }}
        padding="16px"
      >
        <Box marginTop="auto">
          <Text 
            fontWeight="bold" 
            color="white" 
            fontSize="lg"
            marginBottom="8px"
          >{props.creation.title}</Text>

          <Flex gap="8px" alignItems="center">
            <Image 
              borderRadius="999px" 
              src={props.creation.author.profilePictureUrl} 
              width="30px" 
              height="30px" 
            />

            <Text color="white">{props.creation.author.username}</Text>
          </Flex>
        </Box>
      </Flex>
    </Flex>
  )
}

function App() {
  const { data: creations } = useGetCreationsQuery()

  console.log(creations)

  if (!creations) {
    return <Text>Loading...</Text>
  }

  return (
    <Flex maxWidth="100vw" alignItems="center" flexDirection="column" marginX="32px">
      <Grid 
        templateColumns={{ 
          base: "1fr", 
          sm: "repeat(2, 1fr)", 
          md: "repeat(3, 1fr)", 
          lg: "repeat(4, 1fr)" 
        }} 
        gap="8px" 
        maxWidth="1200px" 
        width="100%"
      >
        {creations.map((creation, index) => <CreationCard creation={creation} key={index} />)}
      </Grid>
    </Flex>
  )
}

export default App
