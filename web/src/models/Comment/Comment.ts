import { Author } from "../Author/Author"

export interface Comment {
  id: number
  text: string
  author: Author
}

export interface AddComment {
  text: string
  userId: number
  creationId: number
}

export interface EditComment {
  text: string
}