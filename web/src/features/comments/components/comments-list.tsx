import { Box, Button, Input } from "@chakra-ui/react"
import CommentItem from "./comment-item"
import { Comment } from "@/types/root-types"
import { useAddCommentMutation } from "../api/comments-api"
import { useForm } from "react-hook-form"

interface CommentsListProps {
  comments?: Comment[]
  refetchComments: () => void
  creationId: number
}

export default function CommentsList(props: CommentsListProps) {
  const { register, handleSubmit } = useForm<{ text: string }>()
  const [addComment] = useAddCommentMutation()

  return (
    <Box>
      {props.comments?.map((comment) => (
        <CommentItem
          key={comment.id}
          comment={comment}
          onActionSuccess={props.refetchComments}
        />
      ))}

      <Box
        as="form"
        onSubmit={handleSubmit(async (commentText) => {
          await addComment({
            text: commentText.text,
            creationId: props.creationId,
          })

          props.refetchComments()
        })}
      >
        <Input placeholder="Add a comment" {...register("text")} />
        <Button type="submit">OK</Button>
      </Box>
    </Box>
  )
}
