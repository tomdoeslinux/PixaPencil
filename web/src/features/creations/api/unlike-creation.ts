import { RootEndpointBulder } from "@/api/root-api"

export function unlikeCreation(builder: RootEndpointBulder) {
  return builder.mutation<void, { creationId: number; userId: number }>({
    query: ({ creationId, userId }) => ({
      url: `/creations/${creationId}/unlike`,
      params: { userId },
      method: "POST",
    }),
  })
}
