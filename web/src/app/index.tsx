import { Route, useLocation } from "wouter"
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
  Text,
  Image,
} from "@chakra-ui/react"
import LoginPage from "./routes/login-page"
import RegisterPage from "./routes/register-page"
import { useAppSelector } from "src/store/app-store"
import { ComponentsPage } from "@/components"
import { appLogo } from "@/assets"
import { useEffect, useState } from "react"
import { PXLayout, PXLayoutContent } from "src/components/ui/px-layout"

function AppRouter() {
  return (
    <>
      <Route path="/components" component={ComponentsPage} />
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
          {/* <MdPersonOutline size={20} /> */}
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

function AppBar() {
  const [headerBg, setHeaderBg] = useState("transparent")

  function handleScroll() {
    if (window.scrollY > 15) {
      setHeaderBg("white")
    } else {
      setHeaderBg("transparent")
    }
  }

  useEffect(() => {
    window.addEventListener("scroll", handleScroll)

    return () => {
      window.removeEventListener("scroll", handleScroll)
    }
  }, [])

  return (
    <PXLayout
      position="fixed"
      justifyContent="center"
      height="60px"
      zIndex="999"
      borderBottom="1px solid"
      borderBottomColor={
        headerBg === "transparent" ? "transparent" : "gray.100"
      }
      background={headerBg}
      transition="background 0.3s, border-bottom-color 0.2s"
    >
      <PXLayoutContent flexDirection="row" alignItems="center" gap="12px">
        <Image src={appLogo} width="35px" />
        <Text
          transition="color 0.3s"
          color={headerBg === "transparent" ? "white" : undefined}
        >
          PixaPencil
        </Text>
      </PXLayoutContent>
    </PXLayout>
  )
}

export default function App() {
  const [location] = useLocation()

  return (
    <>
      {location !== "/components" && <AppBar />}
      <AppRouter />
    </>
  )
}
