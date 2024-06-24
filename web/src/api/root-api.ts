import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react"

export const s3BaseUrl =
  "https://pixapencil-gallery.s3.ap-southeast-2.amazonaws.com"
export const baseUrl = "http://localhost:8080/api"

const baseQuery = fetchBaseQuery({
  baseUrl,
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
