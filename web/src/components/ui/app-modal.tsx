import { useEffect, useRef } from "react"
import { Box, BoxProps } from "@chakra-ui/react"

interface AppModalProps extends BoxProps {
  onClose: () => void
}

export default function AppModal({ onClose, ...boxProps }: AppModalProps) {
  const modalRef = useRef<HTMLDialogElement | null>(null)

  useEffect(() => {
    modalRef.current?.showModal()
  }, [])

  return (
    <Box
      as="dialog"
      padding="0px"
      ref={(node) => {
        modalRef.current = node as HTMLDialogElement | null
      }}
      onClick={(e) => {
        const modal = modalRef.current

        if (modal) {
          const rect = modal.getBoundingClientRect()
          const clickedInModal =
            rect.top <= e.clientY &&
            e.clientY <= rect.top + rect.height &&
            rect.left <= e.clientX &&
            e.clientX <= rect.left + rect.width

          if (!clickedInModal) {
            onClose()
          }
        }
      }}
      sx={{
        "::backdrop": {
          background: "rgba(0, 0, 0, 0.6)",
        },
      }}
      {...boxProps}
    />
  )
}
