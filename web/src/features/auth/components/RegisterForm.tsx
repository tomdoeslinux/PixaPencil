import { Box, Button, Input, Spinner } from "@chakra-ui/react"
import {
  RegisterUser,
  useRegisterUserMutation,
  useVerifyUserMutation,
} from "../api/auth-api"
import { useForm } from "react-hook-form"
import { useState } from "react"

enum RegistrationStep {
  InitialRegistration,
  VerifyAccount,
  Complete,
}

interface InitialRegistrationFormProps {
  onRegistrationComplete: (userId: number) => void
}

function InitialRegistrationForm(props: InitialRegistrationFormProps) {
  const { register, handleSubmit } = useForm<RegisterUser>()
  const [registerUser, { isLoading }] = useRegisterUserMutation()

  return (
    <Box
      as="form"
      onSubmit={handleSubmit(async (inputs) => {
        const { data } = await registerUser(inputs)

        if (data && data.id) {
          props.onRegistrationComplete(data.id)
        }
      })}
    >
      <Input placeholder="Username" {...register("username")} />
      <Input placeholder="Email" type="email" {...register("email")} />
      <Input placeholder="Password" type="password" {...register("password")} />
      <Input
        placeholder="Confirm Password"
        type="password"
        {...register("confirmPassword")}
      />

      <Button type="submit">{isLoading ? <Spinner /> : "Register"}</Button>
    </Box>
  )
}

interface VerifyAccountFormProps {
  userId: number
  onVerificationComplete: () => void
}

function VerifyAccountForm(props: VerifyAccountFormProps) {
  const { register, handleSubmit } = useForm<{ code: string }>()
  const [verifyUser, { isLoading }] = useVerifyUserMutation()

  return (
    <Box
      as="form"
      onSubmit={handleSubmit(async (args) => {
        const { data } = await verifyUser({
          userId: props.userId,
          code: args.code,
        })

        if (data) {
          props.onVerificationComplete()
        }
      })}
    >
      <Input placeholder="Enter confirmation code" {...register("code")} />

      <Button type="submit">{isLoading ? <Spinner /> : "Verify"}</Button>
    </Box>
  )
}

export default function RegisterForm() {
  const [userId, setUserId] = useState<number | null>(null)
  const [registrationStep, setRegistrationStep] = useState<RegistrationStep>(
    RegistrationStep.InitialRegistration,
  )

  return (
    <>
      {registrationStep === RegistrationStep.InitialRegistration && (
        <InitialRegistrationForm
          onRegistrationComplete={(userId) => {
            setRegistrationStep(RegistrationStep.VerifyAccount)
            setUserId(userId)
          }}
        />
      )}

      {/** User is created at this point but needs to be verified */}

      {registrationStep === RegistrationStep.VerifyAccount && userId && (
        <VerifyAccountForm
          userId={userId}
          onVerificationComplete={() =>
            setRegistrationStep(RegistrationStep.Complete)
          }
        />
      )}

      {registrationStep === RegistrationStep.Complete && (
        <Box>Account registration complete</Box>
      )}
    </>
  )
}
