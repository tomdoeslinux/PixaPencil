import { Author } from "../Author/Author"

export interface Creation {
  id: number,
  title: string
  description: string
  imageUrl: string
  isLiked: boolean
  likeCount: number
  author: Author
  createdAt: string
}

export interface UploadCreation {
  title: string
  description: string
  file?: File
}
