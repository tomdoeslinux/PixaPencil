import { RootEndpointBulder } from "@/api/root-api"
import { Creation } from "@/types/root-types"

export function getCreation(builder: RootEndpointBulder) {
  return builder.query<Creation, number>({
    query: (creationId) => ({
      url: `/creations/${creationId}`,
    }),
  })
}
