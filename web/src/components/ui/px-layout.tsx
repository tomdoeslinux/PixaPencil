import { Flex, FlexProps } from "@chakra-ui/react"

export function PXLayoutContent(props: FlexProps) {
  return <Flex width="1200px" flexDirection="column" {...props} />
}

export function PXLayout(props: FlexProps) {
  return (
    <Flex width="100%" paddingX="32px" justifyContent="center" {...props} />
  )
}
