import { Box, Flex, IconButton, Image, Text } from "@chakra-ui/react"
import { Creation } from "@/types/root-types"
import { ICOCommentFilled, ICOHeart, ICOHeartFilled } from "@/assets"
import { PXIconButton } from "@/components"

interface CreationCardProps {
  creation: Creation
  onClick: () => void
}

export default function CreationCard(props: CreationCardProps) {
  return (
    <Box>
      <Box
        height="308px"
        position="relative"
        borderRadius="13px"
        overflow="clip"
        _hover={{
          "> *": {
            opacity: 1,
          },
        }}
        cursor="pointer"
        onClick={props.onClick}
      >
        <Image
          left="0"
          top="0"
          position="absolute"
          background="gray"
          objectFit="cover"
          width="100%"
          height="100%"
          src={props.creation.imageUrl}
        />

        <Box
          left="0"
          top="0"
          position="absolute"
          opacity={0}
          width="100%"
          height="100%"
          transition="opacity 0.2s ease-out"
          background="linear-gradient(180deg, rgba(0, 0, 0, 0.00) 64.26%, rgba(0, 0, 0, 0.65) 100%)"
        />

        <Text
          fontWeight="normal"
          position="absolute"
          left="0"
          bottom="0"
          opacity={0}
          color="white"
          margin="22px"
          fontSize="2xl"
        >
          {props.creation.title}
        </Text>
        <PXIconButton
          opacity={0}
          position="absolute"
          bottom="0"
          right="0"
          margin="22px"
          aria-label="Like"
        >
          <ICOHeart />
        </PXIconButton>
      </Box>

      <Flex alignItems="center" marginTop="8px">
        <Image
          src={props.creation.author.profilePictureUrl}
          width="32px"
          height="32px"
          borderRadius="999px"
          marginTop="4px"
        />

        <Box marginLeft="12px">
          <Text fontWeight="600">{props.creation.author.username}</Text>
        </Box>

        <Flex marginLeft="auto" gap="14px">
          <Flex gap="3px" alignItems="center">
            <ICOCommentFilled width="18px" color="#A3A3A3" />
            <Text fontSize="sm">2K</Text>
          </Flex>
          <Flex gap="3px" alignItems="center">
            <ICOHeartFilled width="18px" color="#A3A3A3" />
            <Text fontSize="sm">5K</Text>
          </Flex>
        </Flex>
      </Flex>
    </Box>
  )
}
