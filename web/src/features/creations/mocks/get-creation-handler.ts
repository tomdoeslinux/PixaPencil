import { s3BaseUrl } from "@/api/root-api"
import { Creation } from "@/types/root-types"
import { HttpResponse, http } from "msw"

export default [
  http.get<never, never, Creation>(
    "https://localhost:8080/api/creations/:creationId",
    () =>
      HttpResponse.json({
        id: 6,
        title: "Aurora Borealis",
        description:
          "A mesmerizing view of the Northern Lights over a snowy landscape.",
        imageUrl: `${s3BaseUrl}/pixel_art_3.png`,
        isLiked: false,
        likeCount: 78,
        author: {
          username: "sky_gazer",
          profilePictureUrl: "profile_pic.png",
        },
        uploadDate: "Saturday, June 15, 2024 11:00 PM (Eastern/Greenwich)",
        timeSince: "4 days ago",
      }),
  ),
]
