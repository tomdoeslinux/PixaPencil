import { ICOClose } from "@/assets"
import { Box, Flex, FlexProps } from "@chakra-ui/react"
import { AnimatePresence, motion } from "framer-motion"
import { PropsWithChildren, useEffect, useState } from "react"
import { createPortal } from "react-dom"

type GetModalPropsReturn = (args?: { onClose: () => void }) => PXModalProps

interface UsePXModalReturn {
  showModal: () => void
  getModalProps: GetModalPropsReturn
}

export function usePXModal(): UsePXModalReturn {
  const [isOpen, setIsOpen] = useState(false)

  const getModalProps: GetModalPropsReturn = (args) => {
    return {
      isOpen: isOpen,
      onClose: () => {
        setIsOpen(false)
        args?.onClose()
      },
    }
  }

  return {
    showModal: () => setIsOpen(true),
    getModalProps,
  }
}

interface PXModalBackdropProps extends PropsWithChildren {
  onClick: () => void
}

function PXModalBackdrop(props: PXModalBackdropProps) {
  return (
    <motion.div
      initial={{ background: "rgba(0, 0, 0, 0)" }}
      animate={{ background: "rgba(0, 0, 0, 0.6)" }}
      exit={{ background: "rgba(0, 0, 0, 0)" }}
      onClick={props.onClick}
      style={{
        display: "flex",
        width: "100vw",
        height: "100vh",
        background: "rgba(0, 0, 0, 0.7)",
        left: "0",
        top: "0",
        position: "fixed",
        zIndex: "999",
        alignItems: "flex-start",
        justifyContent: "center",
        overflowY: "auto",
      }}
    >
      {props.children}
    </motion.div>
  )
}

export interface PXModalProps extends FlexProps {
  isOpen: boolean
  onClose: () => void
}

export default function PXModal({ isOpen, onClose, ...props }: PXModalProps) {
  useEffect(() => {
    if (isOpen) {
      document.body.style.overflow = "hidden"
    } else {
      document.body.style.overflow = "auto"
    }
  }, [isOpen])

  return createPortal(
    <AnimatePresence>
      {isOpen && (
        <PXModalBackdrop onClick={onClose}>
          <Box
            position="fixed"
            right="53"
            top="37"
            cursor="pointer"
            onClick={onClose}
          >
            <ICOClose width="34" height="34" color="white" />
          </Box>

          <motion.div
            onClick={(e) => e.stopPropagation()}
            initial={{ scale: 0.95, opacity: 0, y: 30 }}
            animate={{ scale: 1, opacity: 1, y: 0 }}
            exit={{ scale: 0.95, opacity: 0, transition: { duration: 0.1 } }}
            transition={{
              opacity: { duration: 0.3 },
              scale: { duration: 0.2, ease: "easeOut" },
              y: { duration: 0.2, ease: "easeOut" },
            }}
            style={{
              width: "80vw",
              background: "white",
              borderRadius: "16px",
              marginTop: "48px",
            }}
          >
            <Flex flexDirection="column" {...props} />
          </motion.div>
        </PXModalBackdrop>
      )}
    </AnimatePresence>,
    document.body,
  )
}
