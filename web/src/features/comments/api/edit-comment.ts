import { RootEndpointBulder } from "@/api/root-api"

export interface EditComment {
  text: string
  commentId: number
}

export function editComment(builder: RootEndpointBulder) {
  return builder.mutation<void, EditComment>({
    query: (editComment) => ({
      url: `/comments/${editComment.commentId}`,
      body: editComment,
      method: "PUT",
    }),
  })
}
