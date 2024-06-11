import { Box, Button, Input } from "@chakra-ui/react"

export default function LoginForm() {
  return (
    <Box as="form">
      <Input />
      <Input type="password" />

      <Button type="submit">Login</Button>
    </Box>
  )
}
