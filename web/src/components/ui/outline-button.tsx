import { ButtonProps, Button } from "@chakra-ui/react"

export default function OutlineButton(props: ButtonProps) {
  return (
    <Button
      borderRadius="lg"
      variant="ghost"
      border="1px solid"
      borderColor="gray.400"
      {...props}
    />
  )
}
