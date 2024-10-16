import { User } from "@/types/root-types"
import { createSlice } from "@reduxjs/toolkit"
import { authApi } from "../api/auth-api"

interface AuthState {
  isAuthenticated: boolean
  user?: User
}

const initialState: AuthState = {
  isAuthenticated: false,
}

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    logout(state) {
      state.isAuthenticated = false
      state.user = undefined
    },
  },
  extraReducers: (builder) => {
    builder.addMatcher(
      authApi.endpoints.registerUser.matchFulfilled,
      (state, action) => {
        state.isAuthenticated = true
        state.user = action.payload
      },
    )
    builder.addMatcher(
      authApi.endpoints.loginUser.matchFulfilled,
      (state, action) => {
        state.isAuthenticated = true
        state.user = action.payload
      },
    )
  },
})

export const { logout } = authSlice.actions

export default authSlice.reducer
