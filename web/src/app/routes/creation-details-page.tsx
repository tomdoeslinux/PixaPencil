import { CommentsList, useGetCommentsQuery } from "@/features/comments"
import { CreationDetails, useGetCreationQuery } from "@/features/creations"
import { Box, Text } from "@chakra-ui/react"

interface CreationDetailsPageProps {
  creationId: number
}

export default function CreationDetailsPage(props: CreationDetailsPageProps) {
  const { data: creation } = useGetCreationQuery({
    creationId: props.creationId,
    page: 0,
  })

  const { data: commentsResponse, refetch: refetchComments } =
    useGetCommentsQuery({ creationId: creation?.id ?? 0 }, { skip: !creation })
  const comments = commentsResponse?.content

  if (!creation) {
    return <Text>Loading...</Text>
  }

  return (
    <CreationDetails
      creation={creation}
      onNavigation={(arg) => {}}
      commentsSlot={
        <CommentsList refetchComments={refetchComments} comments={comments} />
      }
    />
  )
}
