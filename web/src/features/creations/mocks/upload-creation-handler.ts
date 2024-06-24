import { s3BaseUrl } from "@/api/root-api"
import { S3SignedUrlResponse } from "@/types/root-types"
import { HttpResponse, http } from "msw"

const uploadMockUrl = `${s3BaseUrl}/upload`

export default [
  http.post<{ mimeType: string }, never, S3SignedUrlResponse>(
    "/users/creations/get-upload-url",
    ({ params }) => {
      const mimeType = params.mimeType
      return HttpResponse.json({
        url: uploadMockUrl,
        key: `mock-key-${crypto.randomUUID()}.${mimeType}`,
      })
    },
  ),

  http.put(uploadMockUrl, () => new HttpResponse(null, { status: 200 })),

  http.post(
    "http://localhost:8080/api/creations/upload",
    () =>
      new HttpResponse(null, {
        status: 200,
      }),
  ),
]
