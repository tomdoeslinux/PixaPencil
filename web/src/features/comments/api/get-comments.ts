import { RootEndpointBulder } from "@/api/root-api"
import { Comment, ListResponse } from "@/types/root-types"

export function getComments(builder: RootEndpointBulder) {
  return builder.query<ListResponse<Comment>, { creationId: number }>({
    query: ({ creationId }) => ({
      url: "/comments",
      params: { creationId },
    }),
  })
}
