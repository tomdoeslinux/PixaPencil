import { Flex, Input, InputProps, FlexProps } from "@chakra-ui/react"
import React, { isValidElement } from "react"
import { cloneElement, forwardRef, ReactElement } from "react"

export function PXInputLeftElement({ children, ...props }: FlexProps) {
  return (
    <Flex
      alignItems="center"
      position="absolute"
      left="0"
      top="0"
      bottom="0"
      padding="20px"
      {...props}
    >
      {cloneElement(React.Children.only(children) as ReactElement, {
        color: "#939393",
        width: 18,
        height: 18,
      })}
    </Flex>
  )
}

export function PXInputGroup(props: FlexProps) {
  let leftElement: ReactElement | null = null
  let inputElement: ReactElement | null = null

  React.Children.forEach(props.children, (child) => {
    if (isValidElement(child)) {
      if (child.type === PXInput) {
        inputElement = child
      } else if (child.type === PXInputLeftElement) {
        leftElement = child
      }
    }
  })

  return (
    <Flex alignItems="center" position="relative" {...props}>
      {cloneElement(inputElement!, { paddingLeft: "50px" })}
      {leftElement}
    </Flex>
  )
}

export const PXInput = forwardRef<HTMLInputElement, InputProps>(
  (props, ref) => {
    return (
      <Input
        variant="unstyled"
        height="48px"
        paddingX="18px"
        ref={ref}
        borderRadius="13px"
        background="white"
        fontSize="14px"
        border="1px solid"
        borderColor="gray.200"
        {...props}
      />
    )
  },
)
