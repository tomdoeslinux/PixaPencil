import { RootEndpointBulder } from "@/api/root-api"

export function likeCreation(builder: RootEndpointBulder) {
  return builder.mutation<void, { creationId: number; userId: number }>({
    query: ({ creationId, userId }) => ({
      url: `/creations/${creationId}/like`,
      params: { userId },
      method: "POST",
    }),
  })
}
