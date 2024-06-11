import { Creation } from "@/types/root-types"
import { Box, Heading, Image } from "@chakra-ui/react"
import { ReactNode } from "react"

export interface CreationDetailsProps {
  creation: Creation
  commentsSlot: ReactNode
  onNavigation: (direction: "left" | "right") => void
}

export default function CreationDetails(props: CreationDetailsProps) {
  return (
    <Box>
      <Image src={props.creation.imageUrl} />

      <Heading as="h1" size="lg">
        {props.creation.title}
      </Heading>

      {props.commentsSlot}
    </Box>
  )
}
