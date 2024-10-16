import { PXTextarea, PXButton } from "@/components"
import { Creation } from "@/types/root-types"
import { Flex, Box, Image } from "@chakra-ui/react"

interface AddCommentProps {
  creation: Creation
}

export default function AddComment(props: AddCommentProps) {
  return (
    <Flex gap="23px" marginTop="22px">
      <Image
        width="44px"
        height="44px"
        borderRadius="999px"
        src={props.creation?.author.profilePictureUrl}
      />

      <Box width="560px">
        <PXTextarea
          width="560px"
          placeholder="What are your thoughts on this creation?"
        ></PXTextarea>
        <PXButton
          marginLeft="auto"
          marginTop="13px"
          borderRadius="999px"
          fontSize="13px"
          padding="12px 20px"
        >
          Add Comment
        </PXButton>
      </Box>
    </Flex>
  )
}
