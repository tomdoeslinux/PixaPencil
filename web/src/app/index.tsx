import { Route } from "wouter"
import CreationsPage from "./routes/creations-page"
import CreationDetailsPage from "./routes/creation-details-page"
import {
  Flex,
  Popover,
  PopoverArrow,
  PopoverBody,
  PopoverCloseButton,
  PopoverContent,
  PopoverHeader,
  PopoverTrigger,
} from "@chakra-ui/react"
import { MdPersonOutline } from "react-icons/md"
import LoginPage from "./routes/login-page"
import RegisterPage from "./routes/register-page"
import { useAppDispatch, useAppSelector } from "src/store/app-store"

function AppRouter() {
  return (
    <>
      <Route path="/login" component={LoginPage} />
      <Route path="/register" component={RegisterPage} />
      <Route path="/" component={CreationsPage} />
      <Route path="/creations/:creationId">
        {(params) => (
          <CreationDetailsPage creationId={parseInt(params.creationId)} />
        )}
      </Route>
    </>
  )
}

function Profile() {
  const user = useAppSelector((state) => state.auth.user)

  return (
    <Popover>
      <PopoverTrigger>
        <Flex
          border="1px solid gray"
          width="40px"
          height="40px"
          alignItems="center"
          justifyContent="center"
          marginLeft="auto"
          borderRadius="999px"
          cursor="pointer"
        >
          <MdPersonOutline size={20} />
        </Flex>
      </PopoverTrigger>

      <PopoverContent>
        <PopoverArrow />
        <PopoverCloseButton />
        <PopoverHeader>
          {user?.username} {user?.email}
        </PopoverHeader>
        <PopoverBody>{user?.profilePictureUrl}</PopoverBody>
      </PopoverContent>
    </Popover>
  )
}

export default function App() {
  return (
    <>
      <Flex justifyContent="center" height="60px" shadow="md">
        <Flex maxWidth="1200px" width="100%" alignItems="center" marginX="32px">
          PixaPencil
          <Profile />
        </Flex>
      </Flex>
      <AppRouter />
    </>
  )
}
