import { Box, Button, Input, Spinner } from "@chakra-ui/react"
import { useForm } from "react-hook-form"
import { LoginUser, useLoginUserMutation } from "../api/auth-api"
import { useLocation } from "wouter"

export default function LoginForm() {
  const { register, handleSubmit } = useForm<LoginUser>()
  const [login, { isLoading }] = useLoginUserMutation()

  const [_, setLocation] = useLocation()

  return (
    <Box
      as="form"
      onSubmit={handleSubmit(async (loginArgs) => {
        const result = await login(loginArgs)
        const user = result.data

        if (user) {
          setLocation("/")
        }
      })}
    >
      <Input placeholder="Email" {...register("email")} />
      <Input placeholder="Password" type="password" {...register("password")} />

      <Button type="submit">{isLoading ? <Spinner /> : "Login"}</Button>
    </Box>
  )
}
