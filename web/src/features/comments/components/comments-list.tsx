import { Box, Input } from "@chakra-ui/react"
import CommentItem from "./comment-item"
import { Comment } from "@/types/root-types"

interface CommentsListProps {
  comments?: Comment[]
  refetchComments: () => void
}

export default function CommentsList(props: CommentsListProps) {
  return (
    <Box>
      {props.comments?.map((comment) => (
        <CommentItem
          key={comment.id}
          comment={comment}
          onActionSuccess={props.refetchComments}
        />
      ))}

      <Input placeholder="Add a comment" />
    </Box>
  )
}
