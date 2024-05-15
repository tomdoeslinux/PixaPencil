import { Creation } from "../Creation/Creation";

export interface User {
  username: string
  email: string
  password: string
  creations: Creation[]
  created_at: string
}