import { Box, Flex, Text, Image } from "@chakra-ui/react"
import { Creation } from "@/types/root-types"
import { PXButton, PXTextarea } from "@/components"
import AddComment from "./add-comment"

interface CommentsListProps {
  creation: Creation
}

export default function CommentsList(props: CommentsListProps) {
  return (
    <Box background="#F5F5F5" padding="33px">
      <Box
        padding="27px"
        fontWeight="semibold"
        background="white"
        border="1px solid #E8E8E8"
        borderRadius="13px"
      >
        <Text fontWeight="semibold" fontSize="24px">
          33 Comments
        </Text>

        <AddComment creation={props.creation} />
      </Box>
    </Box>
  )
}
