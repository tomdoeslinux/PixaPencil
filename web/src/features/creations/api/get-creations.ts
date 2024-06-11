import { RootEndpointBulder } from "@/api/root-api"
import { Creation, ListResponse } from "@/types/root-types"

export function getCreations(builder: RootEndpointBulder) {
  return builder.query<ListResponse<Creation>, void>({
    query: () => "/creations/gallery",
  })
}
