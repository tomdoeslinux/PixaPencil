import { User } from "@/types/root-types"
import { createSlice, PayloadAction } from "@reduxjs/toolkit"
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
    onLoggedIn(state, action: PayloadAction<User>) {
      state.isAuthenticated = true
      state.user = action.payload
    },
    onLoggedOut(state) {
      state.isAuthenticated = false
      delete state.user
    },
  },
  extraReducers: (builder) => {
    builder.addMatcher(
      authApi.endpoints.registerUser.matchFulfilled,
      (state) => {
        state.isAuthenticated = true
      },
    )
  },
})

export const { onLoggedIn, onLoggedOut } = authSlice.actions

export default authSlice.reducer
