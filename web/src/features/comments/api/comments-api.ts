import { rootApi } from "@/api/root-api"
import { addComment } from "./add-comment"
import { deleteComment } from "./delete-comment"
import { editComment } from "./edit-comment"
import { getComments } from "./get-comments"

const commentsApi = rootApi.injectEndpoints({
  endpoints: (builder) => ({
    addComment: addComment(builder),
    editComment: editComment(builder),
    deleteComment: deleteComment(builder),
    getComments: getComments(builder),
  }),
})

export const {
  useAddCommentMutation,
  useEditCommentMutation,
  useDeleteCommentMutation,
  useGetCommentsQuery,
} = commentsApi
