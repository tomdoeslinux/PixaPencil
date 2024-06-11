import { ButtonProps, IconButton } from "@chakra-ui/react"
import { MdFavorite, MdFavoriteBorder } from "react-icons/md"
import { useLikeButton } from "../hooks/use-like-button"

export interface LikeButtonIconProps extends ButtonProps {
  onLike: () => void
  onUnlike: () => void
  initialValue: boolean
  color?: string
}

export default function LikeButtonIcon({
  onLike,
  onUnlike,
  initialValue,
  color,
  ...chakraProps
}: LikeButtonIconProps) {
  const { isLiked, toggleLike } = useLikeButton({
    initialValue,
    onLike,
    onUnlike,
  })

  return (
    <IconButton
      variant="ghost"
      _hover={{}}
      _active={{}}
      borderRadius="999px"
      icon={isLiked ? <MdFavorite size={20} /> : <MdFavoriteBorder size={20} />}
      aria-label="Like"
      onClick={toggleLike}
      color={color}
      {...chakraProps}
    />
  )
}
