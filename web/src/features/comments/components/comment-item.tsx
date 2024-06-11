import { Comment } from "@/types/root-types"
import { Flex, Text, Input, Button } from "@chakra-ui/react"
import { useState } from "react"
import { useForm } from "react-hook-form"
import {
  useEditCommentMutation,
  useDeleteCommentMutation,
} from "../api/comments-api"

interface CommentItemProps {
  comment: Comment
  onActionSuccess: () => void
}

export default function CommentItem(props: CommentItemProps) {
  const [isEditing, setIsEditing] = useState(false)
  const { register, handleSubmit } = useForm<{ text: string }>()
  const [editComment] = useEditCommentMutation()
  const [deleteComment] = useDeleteCommentMutation()

  return (
    <Flex>
      {!isEditing ? (
        <Text>{props.comment.text}</Text>
      ) : (
        <Flex
          as="form"
          onSubmit={handleSubmit(async ({ text }) => {
            await editComment({ commentId: props.comment.id, text })
            setIsEditing(false)
            props.onActionSuccess()
          })}
        >
          <Input {...register("text")} defaultValue={props.comment.text} />
          <Button type="submit">OK</Button>
        </Flex>
      )}

      <Flex marginLeft="auto">
        <Button onClick={() => setIsEditing(true)}>Edit</Button>

        <Button
          onClick={async () => {
            await deleteComment(props.comment.id)
            props.onActionSuccess()
          }}
        >
          Delete
        </Button>
      </Flex>
    </Flex>
  )
}
