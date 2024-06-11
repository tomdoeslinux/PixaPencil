import { Flex, Image, Box, Text } from "@chakra-ui/react"
import LikeButtonIcon from "./like-button-icon"
import { Creation } from "@/types/root-types"
import {
  useLikeCreationMutation,
  useUnlikeCreationMutation,
} from "../api/creations-api"

interface CreationCardProps {
  creation: Creation
  onClick: () => void
}

export default function CreationCard(props: CreationCardProps) {
  const [likeCreation] = useLikeCreationMutation()
  const [unlikeCreation] = useUnlikeCreationMutation()

  return (
    <Flex
      flexDirection="column"
      borderRadius="xl"
      overflow="clip"
      shadow="md"
      width="100%"
      cursor="pointer"
      position="relative"
      onClick={props.onClick}
    >
      <Image
        src={props.creation.imageUrl}
        objectFit="cover"
        aspectRatio="1 / 1"
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
        bottom="0"
      >
        <Box marginTop="auto" width="100%">
          <Text
            fontWeight="bold"
            color="white"
            fontSize="lg"
            marginBottom="8px"
          >
            {props.creation.title}
          </Text>

          <Flex gap="8px" alignItems="center" width="100%">
            <Image
              borderRadius="999px"
              src={props.creation.author.profilePictureUrl}
              width="30px"
              height="30px"
            />

            <Text color="white">{props.creation.author.username}</Text>

            <LikeButtonIcon
              onLike={() =>
                likeCreation({ creationId: props.creation.id, userId: 1 })
              }
              onUnlike={() =>
                unlikeCreation({ creationId: props.creation.id, userId: 1 })
              }
              initialValue={props.creation.isLiked}
              color="white"
              marginLeft="auto"
            />
          </Flex>
        </Box>
      </Flex>
    </Flex>
  )
}
