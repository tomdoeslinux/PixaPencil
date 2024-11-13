import { PXInput, PXInputGroup, PXInputLeftElement } from "@/components"
import { Box, Flex, Text } from "@chakra-ui/react"
import { useGetDailyCreationQuery } from "../api/creations-api"
import { ICOSearch } from "@/assets"
import { PXLayout, PXLayoutContent } from "src/components/ui/px-layout"

export default function TopBanner() {
  const { data: dailyCreation } = useGetDailyCreationQuery()

  return (
    <PXLayout height="365px" background="orange" position="relative">
      <Box
        position="absolute"
        left="0"
        right="0"
        top="0"
        bottom="0"
        backgroundPosition="center"
        backgroundRepeat="no-repeat"
        backgroundSize="cover"
        backgroundAttachment="fixed"
        backgroundImage={dailyCreation?.imageUrl}
      />
      <Box
        position="absolute"
        left="0"
        right="0"
        top="0"
        bottom="0"
        background="rgba(0, 0, 0, 0.6)"
      />
      <Text
        fontWeight="light"
        fontSize="xs"
        opacity={0.8}
        color="white"
        position="absolute"
        right="0"
        bottom="0"
        padding="8px"
      >
        Photo by{" "}
        <Text as="span" color="white" textDecoration="underline">
          {dailyCreation?.author.username}
        </Text>
      </Text>
      <PXLayoutContent
        width="430px"
        position="relative"
        justifyContent="center"
      >
        <Flex flexDirection="column" width="100%">
          <Text fontSize="2xl" fontWeight="bold" color="white">
            Welcome to the PixaPencil Gallery
          </Text>
          <PXInputGroup marginTop="24px" width="100%">
            <PXInputLeftElement>
              <ICOSearch />
            </PXInputLeftElement>

            <PXInput placeholder="Search" />
          </PXInputGroup>
        </Flex>
      </PXLayoutContent>
    </PXLayout>
  )
}
