import { Grid } from "@chakra-ui/react"
import { Creation } from "@/types/root-types"
import CreationCard from "./creation-card"

interface CreationsGridProps {
  creations: Creation[]
  onCreationClick: (creation: Creation) => void
}

export default function CreationsGrid(props: CreationsGridProps) {
  return (
    <Grid
      marginTop="24px"
      templateColumns={{
        base: "1fr",
        sm: "repeat(2, 1fr)",
        md: "repeat(3, 1fr)",
      }}
      columnGap="20px"
      rowGap="24px"
    >
      {props.creations.map((creation) => (
        <CreationCard
          creation={creation}
          key={creation.id}
          onClick={() => props.onCreationClick(creation)}
        />
      ))}
    </Grid>
  )
}
