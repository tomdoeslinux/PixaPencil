import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react"

const baseQuery = fetchBaseQuery({
  baseUrl: 'http://localhost:8080/api',
})
  
export const privateApi = createApi({
  baseQuery,
  endpoints: () => ({}),
})
  