import { s3BaseUrl } from "@/api/root-api"
import { Creation, ListResponse } from "@/types/root-types"
import { HttpResponse, http } from "msw"

export default [
  http.get<never, never, ListResponse<Creation>>(
    "http://localhost:8080/api/creations/gallery",
    () => {
      return HttpResponse.json({
        content: [
          {
            id: 1,
            title: "Sunset Over the Mountains",
            description: "A beautiful sunset over the rocky mountains.",
            imageUrl: `${s3BaseUrl}/pixel_art_1.png`,
            isLiked: true,
            likeCount: 120,
            author: {
              username: "jane_doe92",
              profilePictureUrl: "profile_pic.png",
            },
            uploadDate: "Monday, June 10, 2024 3:30 PM (Pacific/Auckland)",
            timeSince: "5 days ago",
          },
          {
            id: 2,
            title: "Ocean Breeze",
            description: "A calming scene of the ocean waves.",
            imageUrl: `${s3BaseUrl}/pixel_art_2.png`,
            isLiked: false,
            likeCount: 85,
            author: {
              username: "mike_smith01",
              profilePictureUrl: "profile_pic.png",
            },
            uploadDate: "Monday, June 10, 2024 10:00 AM (America/New_York)",
            timeSince: "6 days ago",
          },
          {
            id: 3,
            title: "City Lights",
            description: "The city skyline illuminated at night.",
            imageUrl: `${s3BaseUrl}/pixel_art_3.png`,
            isLiked: true,
            likeCount: 200,
            author: {
              username: "alexandra.k",
              profilePictureUrl:
                "Tuesday, June 11, 2024 1:30 PM (Pacific/Auckland)",
            },
            uploadDate: "Sunday, June 9, 2024 8:45 PM (Europe/London)",
            timeSince: "7 days ago",
          },
          {
            id: 4,
            title: "Autumn Leaves",
            description: "The vibrant colors of autumn leaves in a forest.",
            imageUrl: `${s3BaseUrl}/pixel_art_4.png`,
            isLiked: false,
            likeCount: 95,
            author: {
              username: "chris_j87",
              profilePictureUrl: "profile_pic.png",
            },
            uploadDate: "Saturday, June 8, 2024 3:20 PM (Asia/Tokyo)",
            timeSince: "8 days ago",
          },
          {
            id: 5,
            title: "Mountain Peak",
            description: "A breathtaking view from the top of a mountain.",
            imageUrl: `${s3BaseUrl}/pixel_art_5.png`,
            isLiked: true,
            likeCount: 150,
            author: {
              username: "emily_rose23",
              profilePictureUrl: "profile_pic.png",
            },
            uploadDate: "Friday, June 7, 2024 9:00 AM (Australia/Sydney)",
            timeSince: "9 days ago",
          },
        ],
      })
    },
  ),
]
