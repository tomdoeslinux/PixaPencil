import { ICOComment } from "@/assets"
import { Box, Button, ButtonProps } from "@chakra-ui/react"

type PXButtonVariant = "filled" | "outlined"

function getButtonStylesForVariant(variant: PXButtonVariant): ButtonProps {
  switch (variant) {
    case "filled":
      return {
        background: "px.blue.med",
        color: "white",
        _hover: { background: "px.blue.dark" },
        _active: { background: "px.blue.darkest" },
      }
    case "outlined":
      return {
        background: "white",
        border: "1px solid",
        borderColor: "px.gray",
        _hover: { background: "gray.100" },
        _active: { background: "gray.200" },
      }
  }
}

interface PXButtonProps extends ButtonProps {
  variant?: PXButtonVariant
}

export default function PXButton({
  variant = "filled",
  leftIcon,
  ...props
}: PXButtonProps) {
  return (
    <Button
      variant="unstyled"
      display="flex"
      borderRadius="13px"
      height="fit-content"
      padding="14px 24px"
      {...getButtonStylesForVariant(variant)}
      {...props}
      leftIcon={
        leftIcon ? (
          <Box color={variant === "filled" ? "white" : "#A3A3A3"}>
            {leftIcon}
          </Box>
        ) : undefined
      }
    />
  )
}
