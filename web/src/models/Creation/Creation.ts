interface Author {
  username: string
  profilePictureUrl: string
}

export interface Creation {
  title: string
  description: string
  imageUrl: string
  author: Author
  createdAt: string
}

export interface UploadCreation {
  title: string
  description: string
  file?: File
}
