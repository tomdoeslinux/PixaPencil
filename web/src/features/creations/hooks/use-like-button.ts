import { useEffect, useRef, useState } from "react"

interface UseLikeButtonProps {
  initialValue: boolean
  onLike: () => void
  onUnlike: () => void
}

interface UseLikeButtonReturn {
  isLiked: boolean
  toggleLike: (e: React.MouseEvent) => void
}

export function useLikeButton(props: UseLikeButtonProps): UseLikeButtonReturn {
  const [isLiked, setIsLiked] = useState(props.initialValue)
  const prevIsLiked = useRef(isLiked)

  useEffect(() => {
    if (prevIsLiked.current !== isLiked) {
      if (isLiked) {
        props.onLike()
      } else {
        props.onUnlike()
      }

      prevIsLiked.current = isLiked
    }
  }, [isLiked])

  function toggleLike() {
    setIsLiked((prevIsLiked) => !prevIsLiked)
  }

  return {
    toggleLike,
    isLiked,
  }
}
