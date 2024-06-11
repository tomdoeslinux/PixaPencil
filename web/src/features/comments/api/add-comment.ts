import { RootEndpointBulder } from "@/api/root-api"

export interface AddComment {
  text: string
  creationId: number
}

export function addComment(builder: RootEndpointBulder) {
  return builder.mutation<void, AddComment>({
    query: (addComment) => ({
      url: "/comments",
      body: addComment,
      method: "POST",
    }),
  })
}
