import { Box, Text, Flex, Image } from "@chakra-ui/react"
import { Creation } from "@/types/root-types"
import { PXButton, PXInput } from "@/components"
import { googleLogo } from "@/assets"

interface LoginForm {
  dailyCreation: Creation
}

export default function LoginForm(props: LoginForm) {
  // should main page also have background pos center for main img?
  return (
    <Flex
      background={`url(${props.dailyCreation.imageUrl})`}
      sx={{ imageRendering: "pixelated" }}
      backgroundAttachment="fixed"
      backgroundPosition="center"
      width="100vw"
      height="100vh"
      backgroundSize="cover"
      alignItems="center"
      justifyContent="center"
    >
      <Flex
        flexDirection="column"
        width="600px"
        padding="64px"
        background="white"
        borderRadius="xl"
      >
        <Text fontSize="4xl">Login</Text>

        <Box marginTop="32px">
          <Text>Username</Text>
          <PXInput marginTop="6px" placeholder="E.g: user@example.com" />
        </Box>

        <Box marginTop="32px">
          <Text>Password</Text>
          <PXInput marginTop="6px" placeholder="Enter password" />
        </Box>

        <PXButton marginTop="32px" width="100%">
          Login
        </PXButton>

        <Flex marginY="24px" alignItems="center" gap="24px">
          <Box flexGrow={1} borderTop="1px solid" borderColor="gray.100" />
          <Text textAlign="center">Or</Text>
          <Box flexGrow={1} borderTop="1px solid" borderColor="gray.100" />
        </Flex>

        <PXButton
          variant="outlined"
          leftIcon={<Image width="20px" src={googleLogo} />}
        >
          Continue with Google
        </PXButton>

        <Text marginTop="32px" alignSelf="center">
          No account?{" "}
          <Box
            as="span"
            cursor="pointer"
            color="px.blue.med"
            textUnderlineOffset="4px"
            _hover={{ textDecoration: "underline" }}
          >
            Create one!
          </Box>
        </Text>
      </Flex>
    </Flex>
  )
}
