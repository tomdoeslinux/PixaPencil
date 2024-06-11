import { RootEndpointBulder } from "@/api/root-api"
import { Creation } from "@/types/root-types"

export function getCreation(builder: RootEndpointBulder) {
  return builder.query<Creation, { creationId: number; page: number }>({
    query: ({ creationId, page }) => ({
      url: `/creations/${creationId}`,
      params: { page },
    })
  })
}
