import { Box } from "@chakra-ui/react"
import { PropsWithChildren } from "react"

export interface PXTagProps extends PropsWithChildren {
  isSelected?: boolean
  onClick?: () => void
}

export default function PXTag(props: PXTagProps) {
  return (
    <Box
      padding="8px 17px"
      borderRadius="999px"
      background={props.isSelected ? "#F5F5F5" : "white"}
      cursor="pointer"
      onClick={props.onClick}
      fontWeight="600"
      transition="background 0.3s"
      border="1px solid"
      borderColor="gray.200"
      fontSize="14px"
      _hover={{ color: !props.isSelected ? "gray" : undefined }}
    >
      {props.children}
    </Box>
  )
}
