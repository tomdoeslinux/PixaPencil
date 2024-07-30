import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react"

export const s3BaseUrl =
  "https://pixapencil-gallery.s3.ap-southeast-2.amazonaws.com"
export const baseUrl = "http://localhost:8080/api"

const AUTH_REQUIRED_ENDPOINTS = [
  "addComment",
  "deleteComment",
  "editComment",
  "likeCreation",
  "unlikeCreation",
  "uploadCreation",
]

const baseQuery = fetchBaseQuery({
  baseUrl,
  prepareHeaders: (headers, { endpoint }) => {
    console.log("Endpoint " + endpoint)
    if (AUTH_REQUIRED_ENDPOINTS.includes(endpoint)) {
      const username = "tomdoeslinux"
      const password = "password"
      const basicAuth = btoa(`${username}:${password}`)
      headers.set("Authorization", `Basic ${basicAuth}`)
    }

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
