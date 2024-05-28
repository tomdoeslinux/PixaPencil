import { privateApi } from "../api/privateApi";
import { UploadCreation } from "src/models/Creation/Creation";

const extendedApi = privateApi.injectEndpoints({
  endpoints: (builder) => ({
    uploadCreation: builder.mutation<undefined, { userId: number, uploadCreation: UploadCreation }>({
      queryFn: async ({ userId, uploadCreation }, _, __, fetchWithBQ) => {
        const mimeType = uploadCreation.file!.type
        
        const getUploadUrlResponse = await fetchWithBQ({
          url: 'users/creations/get-upload-url',
          params: { mimeType },
          method: 'GET'
        })

        if (getUploadUrlResponse.error) {
          return { error: getUploadUrlResponse.error }
        }

        const getUploadUrlResponseData = getUploadUrlResponse.data as { url: string, key: string }
        
        await fetch(getUploadUrlResponseData.url, {
          method: 'PUT',
          headers: {
            'Content-Type': mimeType
          },
          body: uploadCreation.file
        })

        delete uploadCreation.file

        const addCreationResponse = await fetchWithBQ({
          url: `/users/${userId}/creations`,
          method: 'POST',
          body: { ...uploadCreation, imageKey: getUploadUrlResponseData.key }
        })

        if (addCreationResponse.error) {
          return { error: addCreationResponse.error }
        }

        return { data: undefined }
      }
    })
  })
})


export const { useUploadCreationMutation } = extendedApi