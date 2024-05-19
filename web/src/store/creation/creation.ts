import { Creation } from "models";
import { privateApi } from "../api/privateApi";

const extendedApi = privateApi.injectEndpoints({
  endpoints: (builder) => ({
    getCreations: builder.query<Creation[], void>({
      query: () => '/creations'
    })
  })
})

export const { useGetCreationsQuery } = extendedApi