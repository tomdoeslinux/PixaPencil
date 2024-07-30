import {
  useLikeCreationMutation,
  useUnlikeCreationMutation,
} from "../api/creations-api"
import { CreationDetailsProps } from "./creation-details"
import { useArrowKeyNav } from "../hooks/use-arrow-key-nav"
import { AppModal, AuthorTag, OutlineButton } from "@/components"
import { Flex, Heading, Box, Text, Image } from "@chakra-ui/react"
import { MdChatBubbleOutline, MdOutlineVisibility } from "react-icons/md"
import LikeButtonCounter from "./like-button-counter"

interface CreationDetailsPopupProps extends CreationDetailsProps {
  onClose: () => void
}

export default function CreationDetailsPopup(props: CreationDetailsPopupProps) {
  const [likeCreation] = useLikeCreationMutation()
  const [unlikeCreation] = useUnlikeCreationMutation()

  useArrowKeyNav((dir) => props.onNavigation(dir))

  return (
    <AppModal onClose={props.onClose} display="flex">
      <Image
        background="black"
        maxWidth="700px"
        maxHeight="600px"
        objectFit="contain"
        src={props.creation.imageUrl}
      />

      <Flex flexDirection="column" width="400px">
        <AuthorTag
          author={props.creation.author}
          subheading={{
            text: props.creation.timeSince,
            tooltip: props.creation.uploadDate,
          }}
        />

        <Box paddingLeft="16px" borderTop="1px solid" borderTopColor="gray.300">
          <Heading as="h1" size="lg" marginTop="16px">
            {props.creation.title}
          </Heading>
          <Text marginTop="12px">{props.creation.description}</Text>
        </Box>

        <Flex padding="12px 16px" marginTop="8px" gap="8px">
          <LikeButtonCounter
            key={props.creation.id}
            onLike={() =>
              likeCreation({ creationId: props.creation.id, userId: 1 })
            }
            onUnlike={() =>
              unlikeCreation({ creationId: props.creation.id, userId: 1 })
            }
            color="black"
            initialValue={props.creation.isLiked}
            width="100%"
            initialLikeCount={props.creation.likeCount}
          />
          <OutlineButton leftIcon={<MdChatBubbleOutline />} width="100%">
            0
          </OutlineButton>
          <OutlineButton leftIcon={<MdOutlineVisibility />} width="100%">
            0
          </OutlineButton>
        </Flex>

        {props.commentsSlot}
      </Flex>
    </AppModal>
  )
}
