import { IconButton, IconButtonProps } from "@chakra-ui/react"
import React, { ReactElement } from "react"
import { cloneElement } from "react"

export default function PXIconButton({ children, ...props }: IconButtonProps) {
  return (
    <IconButton
      width="fit-content"
      background="white"
      color="gray.600"
      borderRadius="999px"
      {...props}
    >
      {children}
    </IconButton>
  )
}
