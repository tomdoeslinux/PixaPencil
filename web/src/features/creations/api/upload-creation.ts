import { RootEndpointBulder, RootFetchWithBQ } from "@/api/root-api"

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
    url: "users/creations/get-upload-url",
    params: { mimeType },
    method: "GET",
  })

  if (response.error) {
    throw response.error
  }

  return response.data as {
    url: string
    key: string
  }
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
  userId: number,
  key: string,
  fetchWithBQ: RootFetchWithBQ,
) {
  const response = await fetchWithBQ({
    url: `/users/${userId}/creations`,
    method: "POST",
    body: { ...uploadCreation, imageKey: key },
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

      await addCreation(userId, getUploadUrlResponseData.key, fetchWithBQ)

      return { data: undefined }
    },
  })
}
