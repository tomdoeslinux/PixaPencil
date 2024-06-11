import { rootApi } from "@/api/root-api"
import { getCreation } from "./get-creation"
import { getCreations } from "./get-creations"
import { likeCreation } from "./like-creations"
import { unlikeCreation } from "./unlike-creation"
import { uploadCreation } from "./upload-creation"

const creationsApi = rootApi.injectEndpoints({
  endpoints: (builder) => ({
    getCreation: getCreation(builder),
    getCreations: getCreations(builder),
    likeCreation: likeCreation(builder),
    unlikeCreation: unlikeCreation(builder),
    uploadCreation: uploadCreation(builder),
  }),
})

export const {
  useGetCreationQuery,
  useGetCreationsQuery,
  useLikeCreationMutation,
  useUnlikeCreationMutation,
  useUploadCreationMutation,
} = creationsApi
