import { rootApi } from "@/api/root-api"

export interface RegisterUser {
  username: string
  email: string
  password: string
  confirmPassword: string
}

export interface LoginUser {
  email: string
  password: string
}

export interface GetUser {
  id?: number
  username: string
  email: string
  profilePictureUrl: string
}

export const authApi = rootApi.injectEndpoints({
  endpoints: (builder) => ({
    registerUser: builder.mutation<GetUser, RegisterUser>({
      query: (registerUser) => ({
        url: "/users/register",
        method: "POST",
        body: registerUser,
      }),
    }),
    verifyUser: builder.mutation<boolean, { userId: number; code: string }>({
      query: ({ userId, code }) => ({
        url: "/users/verify",
        method: "POST",
        params: { userId, code },
      }),
    }),
    loginUser: builder.mutation<boolean, LoginUser>({
      query: (loginUser) => ({
        url: "/users/login",
        method: "POST",
        body: loginUser,
      }),
    }),
  }),
})

export const {
  useLoginUserMutation,
  useRegisterUserMutation,
  useVerifyUserMutation,
} = authApi
