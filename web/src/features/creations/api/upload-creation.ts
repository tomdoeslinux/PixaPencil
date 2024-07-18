import { RootEndpointBulder, RootFetchWithBQ } from "@/api/root-api"
import { S3SignedUrlResponse } from "@/types/root-types"

export interface UploadCreation {
  title: string
  description: string
  file?: File
}

async function getCreationUploadUrl(
  mimeType: string,
  fetchWithBQ: RootFetchWithBQ,
) {
  const response = await fetchWithBQ({
    url: "/creations/get-upload-url",
    params: { mimeType },
    method: "GET",
  })

  if (response.error) {
    throw response.error
  }

  return response.data as S3SignedUrlResponse
}

async function uploadFileFromCreationUploadUrl(
  url: string,
  file: File,
  mimeType: string,
) {
  // We are using native fetch here since domain (S3) is not part of our backend
  const response = await fetch(url, {
    method: "PUT",
    headers: {
      "Content-Type": mimeType,
    },
    body: file,
  })

  if (!response.ok) {
    throw new Error()
  }
}

async function addCreation(
  imageKey: string,
  uploadCreation: UploadCreation,
  fetchWithBQ: RootFetchWithBQ,
) {
  const response = await fetchWithBQ({
    url: `/creations/upload`,
    method: "POST",
    body: { ...uploadCreation, imageKey },
  })

  if (response.error) {
    throw response.error
  }
}

export function uploadCreation(builder: RootEndpointBulder) {
  return builder.mutation<
    undefined,
    { userId: number; uploadCreation: UploadCreation }
  >({
    queryFn: async ({ userId, uploadCreation }, _, __, fetchWithBQ) => {
      console.log(uploadCreation)
      const mimeType = uploadCreation.file!.type

      const getUploadUrlResponseData = await getCreationUploadUrl(
        mimeType,
        fetchWithBQ,
      )

      await uploadFileFromCreationUploadUrl(
        getUploadUrlResponseData.url,
        uploadCreation.file!,
        mimeType,
      )

      delete uploadCreation.file

      await addCreation(getUploadUrlResponseData.key, uploadCreation, fetchWithBQ)

      return { data: undefined }
    },
  })
}
