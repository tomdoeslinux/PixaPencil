import { Textarea, TextareaProps } from "@chakra-ui/react"
import { forwardRef } from "react"

const PXTextarea = forwardRef<HTMLTextAreaElement, TextareaProps>(
  (props, ref) => {
    return (
      <Textarea
        paddingY="13px"
        paddingX="18px"
        width="fit-content"
        ref={ref}
        borderRadius="13px"
        {...props}
      />
    )
  },
)

export default PXTextarea
