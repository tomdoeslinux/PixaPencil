import { Comment, Creation } from "models";
import { privateApi } from "../api/privateApi";

const extendedApi = privateApi.injectEndpoints({
  endpoints: (builder) => ({
    getCreations: builder.query<Creation[], void>({
      query: () => '/creations'
    }),

    likeCreation: builder.mutation<void, { creationId: number, userId: number }>({
      query: ({ creationId, userId }) => ({ 
        url: `/creations/${creationId}/like`,
        params: { userId },
        method: 'POST'
      }),
    }),

    unlikeCreation: builder.mutation<void, { creationId: number, userId: number }>({
      query: ({ creationId, userId }) => ({
        url: `/creations/${creationId}/unlike`,
        params: { userId },
        method: 'POST'
      }),
    }),

    getCreationComments: builder.query<Comment[], number>({
      query: (creationId) => `/creations/${creationId}/comments`
    })
  })
})

export const { useGetCreationsQuery, useLikeCreationMutation, useUnlikeCreationMutation, useGetCreationCommentsQuery } = extendedApi