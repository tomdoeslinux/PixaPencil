import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { Box, Flex, Grid, IconButton, Image } from '@chakra-ui/react'
import { Creation } from 'models'
import { MdFavorite, MdFavoriteBorder } from 'react-icons/md'

const creations: Creation[] = [
  { title: "Pixel Forest", description: "A serene pixel art of a dense, mystical forest.", cover_image_url: "https://picsum.photos/id/100/300/300", created_at: "2024-04-01T12:00:00Z" },
  { title: "Space Invader Tribute", description: "A tribute to the classic Space Invaders game in pixel art.", cover_image_url: "https://picsum.photos/id/101/300/300", created_at: "2024-04-02T12:00:00Z" },
  { title: "Pixel Sunrise", description: "A vibrant pixel sunrise over a peaceful lake.", cover_image_url: "https://picsum.photos/id/102/300/300", created_at: "2024-04-03T12:00:00Z" },
  { title: "Castle Pixels", description: "A detailed pixel art of an ancient castle at sunset.", cover_image_url: "https://picsum.photos/id/103/300/300", created_at: "2024-04-04T12:00:00Z" },
  { title: "Pixel City", description: "A bustling pixel cityscape at night.", cover_image_url: "https://picsum.photos/id/104/300/300", created_at: "2024-04-05T12:00:00Z" },
  { title: "Dragon's Lair", description: "A pixel art depicting a dragon guarding its lair.", cover_image_url: "https://picsum.photos/id/105/300/300", created_at: "2024-04-06T12:00:00Z" },
  { title: "Pixel Ocean", description: "Calm pixelated ocean waves under a starry sky.", cover_image_url: "https://picsum.photos/id/106/300/300", created_at: "2024-04-07T12:00:00Z" },
  { title: "Desert Pixel", description: "A pixel art of a vast desert with a lone cactus.", cover_image_url: "https://picsum.photos/id/107/300/300", created_at: "2024-04-08T12:00:00Z" },
  { title: "Pixel Diner", description: "A cozy diner scene in pixel art style.", cover_image_url: "https://picsum.photos/id/108/300/300", created_at: "2024-04-09T12:00:00Z" },
  { title: "Underwater Pixels", description: "A pixel art adventure in a vibrant underwater world.", cover_image_url: "https://picsum.photos/id/109/300/300", created_at: "2024-04-10T12:00:00Z" },
  { title: "Haunted Pixel House", description: "A spooky haunted house rendered in pixel art.", cover_image_url: "https://picsum.photos/id/110/300/300", created_at: "2024-04-11T12:00:00Z" },
  { title: "Pixel Robots", description: "A scene of pixel robots in a futuristic setting.", cover_image_url: "https://picsum.photos/id/111/300/300", created_at: "2024-04-12T12:00:00Z" },
  { title: "Retro Game Scene", description: "Pixel art inspired by retro video games.", cover_image_url: "https://picsum.photos/id/112/300/300", created_at: "2024-04-13T12:00:00Z" },
  { title: "Pixel Beach", description: "Sandy beach with pixelated waves and a setting sun.", cover_image_url: "https://picsum.photos/id/113/300/300", created_at: "2024-04-14T12:00:00Z" },
  { title: "Medieval Pixels", description: "A medieval town scene in detailed pixel art.", cover_image_url: "https://picsum.photos/id/114/300/300", created_at: "2024-04-15T12:00:00Z" },
  { title: "Pixel Carnival", description: "A colorful pixel art of a lively carnival scene.", cover_image_url: "https://picsum.photos/id/115/300/300", created_at: "2024-04-16T12:00:00Z" },
  { title: "Winter Wonderland", description: "A snowy landscape in charming pixel art style.", cover_image_url: "https://picsum.photos/id/116/300/300", created_at: "2024-04-17T12:00:00Z" },
  { title: "Pixel Mountains", description: "Majestic mountains in pixel art.", cover_image_url: "https://picsum.photos/id/117/300/300", created_at: "2024-04-18T12:00:00Z" },
  { title: "Garden Pixels", description: "A pixel art of a lush garden in spring.", cover_image_url: "https://picsum.photos/id/118/300/300", created_at: "2024-04-19T12:00:00Z" },
  { title: "Urban Pixel Life", description: "Urban life captured in vibrant pixel art.", cover_image_url: "https://picsum.photos/id/119/300/300", created_at: "2024-04-20T12:00:00Z" },
  { title: "Pixel Farm", description: "A farm scene depicted in classic pixel art.", cover_image_url: "https://picsum.photos/id/120/300/300", created_at: "2024-04-21T12:00:00Z" },
  { title: "Neon Pixels", description: "Neon lights in a pixel art city.", cover_image_url: "https://picsum.photos/id/121/300/300", created_at: "2024-04-22T12:00:00Z" },
  { title: "Pixel Galaxy", description: "Explore the cosmos in this pixel galaxy art.", cover_image_url: "https://picsum.photos/id/122/300/300", created_at: "2024-04-23T12:00:00Z" },
  { title: "Ancient Pixels", description: "Ancient ruins brought to life through pixel art.", cover_image_url: "https://picsum.photos/id/123/300/300", created_at: "2024-04-24T12:00:00Z" },
  { title: "Pixel Voyage", description: "A pixel art of a historical sea voyage.", cover_image_url: "https://picsum.photos/id/124/300/300", created_at: "2024-04-25T12:00:00Z" },
  { title: "Cyberpunk City", description: "A cyberpunk cityscape in pixel art.", cover_image_url: "https://picsum.photos/id/125/300/300", created_at: "2024-04-26T12:00:00Z" },
  { title: "Pixel Portraits", description: "Famous portraits reimagined in pixel art.", cover_image_url: "https://picsum.photos/id/126/300/300", created_at: "2024-04-27T12:00:00Z" },
  { title: "Retro Arcade", description: "The excitement of a retro arcade in pixel form.", cover_image_url: "https://picsum.photos/id/127/300/300", created_at: "2024-04-28T12:00:00Z" },
  { title: "Fantasy Pixel Realm", description: "A fantasy realm depicted in beautiful pixel art.", cover_image_url: "https://picsum.photos/id/128/300/300", created_at: "2024-04-29T12:00:00Z" },
  { title: "Pixel Sports", description: "Various sports scenes captured in pixel art.", cover_image_url: "https://picsum.photos/id/129/300/300", created_at: "2024-04-30T12:00:00Z" }
]

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

function CreationCard(props: CreationCardProps) {
  return (
    <Flex 
      flexDirection="column" 
      borderRadius="3px" 
      overflow="clip" 
      shadow="md"
      width="100%"
    >
      <Image src={props.creation.cover_image_url} objectFit="cover" height="200px" width="100%" />
      <Flex alignItems="center" paddingLeft="16px" height="50px">
        {props.creation.title}
        <LikeButton onLike={() => {}} onUnlike={() => {}} />
      </Flex>
    </Flex>
  )
}

function App() {
  return (
    <Flex maxWidth="100vw" alignItems="center" flexDirection="column">
      <Grid 
        templateColumns={{ base: "1fr", sm: "repeat(2, 1fr)", md: "repeat(3, 1fr)" }} 
        gap="8px" 
        maxWidth="800px" 
        width="100%"
      >
        {creations.map((creation, index) => <CreationCard creation={creation} key={index} />)}
      </Grid>
    </Flex>
  )
}

export default App
