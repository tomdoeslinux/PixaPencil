interface Author {
  username: string
  profilePictureUrl: string
}

export interface Creation {
  title: string
  description: string
  coverImageUrl: string
  author: Author
  createdAt: string
}

export interface AddCreation {
  title: string
  description: string
  coverImageUrl: string
}
