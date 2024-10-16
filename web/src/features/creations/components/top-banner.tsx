import { PXInput, PXInputGroup, PXInputLeftElement } from "@/components"
import { Box, Flex, Text } from "@chakra-ui/react"
import { useGetDailyCreationQuery } from "../api/creations-api"
import { ICOSearch } from "@/assets"

export default function TopBanner() {
  const { data: dailyCreation } = useGetDailyCreationQuery()

  return (
    <Flex
      height="365px"
      background="gray"
      alignItems="center"
      justifyContent="center"
      flexDirection="column"
      gap="22px"
      width="100%"
      position="relative"
    >
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

      <Flex
        right="0"
        left="0"
        position="absolute"
        alignItems="center"
        justifyContent="center"
        flexDirection="column"
        gap="16px"
      >
        <Box>
          <Text width="400px" color="white" fontSize="2xl" fontWeight="bold">
            Welcome to the PixaPencil Gallery
          </Text>
          <PXInputGroup marginTop="24px">
            <PXInputLeftElement>
              <ICOSearch />
            </PXInputLeftElement>

            <PXInput width="430px" placeholder="Search" />
          </PXInputGroup>
        </Box>
      </Flex>

      <Text
        color="white"
        position="absolute"
        padding="24px"
        bottom="0"
        right="0"
        fontSize="xs"
      >
        Photo by {dailyCreation?.author.username}
      </Text>
    </Flex>
  )
}
