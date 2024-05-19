import { AddCreation } from "models";
import { privateApi } from "../api/privateApi";

const extendedApi = privateApi.injectEndpoints({
  endpoints: (builder) => ({
    addCreation: builder.mutation<{ userId: number, addCreation: AddCreation }, void>({
      queryFn: () => {
        
      }
    })
  })
})