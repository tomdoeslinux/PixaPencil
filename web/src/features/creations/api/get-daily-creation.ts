import { RootEndpointBulder } from "@/api/root-api"
import { Creation } from "@/types/root-types"

export function getDailyCreation(builder: RootEndpointBulder) {
  return builder.query<Creation, void>({
    query: () => "/creations/daily-creation",
  })
}
