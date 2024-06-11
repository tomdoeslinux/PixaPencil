import { RootEndpointBulder } from "@/api/root-api"

export function deleteComment(builder: RootEndpointBulder) {
  return builder.mutation<void, number>({
    query: (id) => ({
      url: `/comments/${id}`,
      method: "DELETE",
    }),
  })
}
