import { AddComment } from "src/models/Comment/Comment";
import { privateApi } from "../api/privateApi";

const extendedApi = privateApi.injectEndpoints({
  endpoints: (builder) => ({
    addComment: builder.mutation<void, AddComment>({
      query: (addComment) => ({ 
        url: `/comments`,
        body: addComment,
        method: 'POST'
      }),
    }),

    deleteComment: builder.mutation<void, number>({
      query: (id) => ({
        url: `/comments/${id}`,
        method: 'DELETE'
      })
    })
  })
})

export const { useAddCommentMutation, useDeleteCommentMutation } = extendedApi