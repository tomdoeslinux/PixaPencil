import { Author } from "@/types/root-types"
import { Box, Flex, Image, Text, Tooltip } from "@chakra-ui/react"

interface AuthorTagProps {
  author: Author
  subheading?: { text: string, tooltip?: string } | string
}

export default function AuthorTag(props: AuthorTagProps) {
  return (
    <Flex
      width="100%"
      alignItems="center"
      borderBottom="1px solid"
      borderBottomColor="gray.200"
      padding="12px 16px"
    >
      <Image
        src={props.author.profilePictureUrl}
        width="35px"
        height="35px"
        borderRadius="999px"
        marginRight="14px"
      />
      <Box>
        <Text fontWeight="bold">{props.author.username}</Text>

        {props.subheading && (
          <>
            {typeof props.subheading === "object" ? (
              <Tooltip label={props.subheading.tooltip} fontSize="14px">
                <Text fontSize="12px">{props.subheading.text}</Text>
              </Tooltip>
            ) : (
              <Text fontSize="12px">{props.subheading}</Text>
            )}
          </>
        )}
      </Box>
    </Flex>
  )
}
