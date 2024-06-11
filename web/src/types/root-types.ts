export interface Author {
  username: string
  profilePictureUrl: string
}

export interface ListResponse<T> {
  content: T[]
}

export interface Comment {
  id: number
  text: string
  author: Author
}

export interface Creation {
  id: number
  title: string
  description: string
  imageUrl: string
  isLiked: boolean
  likeCount: number
  author: Author
  uploadDate: string
  timeSince: string
}
