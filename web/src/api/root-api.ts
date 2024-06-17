import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react"

const baseQuery = fetchBaseQuery({
  baseUrl: "http://localhost:8080/api",
  prepareHeaders: (headers) => {
    const username = "tomdoeslinux"
    const password = "password"
    const basicAuth = btoa(`${username}:${password}`)
    headers.set("Authorization", `Basic ${basicAuth}`)

    const timeZone = Intl.DateTimeFormat().resolvedOptions().timeZone
    headers.set("X-Time-Zone", timeZone)

    return headers
  },
})

export const rootApi = createApi({
  baseQuery,
  endpoints: () => ({}),
})

export type RootEndpointBulder = Parameters<
  Parameters<typeof rootApi.injectEndpoints>[0]["endpoints"]
>[0]

export type RootFetchWithBQ = Parameters<
  Exclude<Parameters<RootEndpointBulder["mutation"]>[0]["queryFn"], undefined>
>[3]
