import { CommentsList, useGetCommentsQuery } from "@/features/comments"
import { CreationDetails, useGetCreationQuery } from "@/features/creations"
import { Text } from "@chakra-ui/react"

interface CreationDetailsPageProps {
  creationId: number
}

export default function CreationDetailsPage(props: CreationDetailsPageProps) {
  const { data: creation } = useGetCreationQuery(props.creationId)

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
        <CommentsList
          creationId={props.creationId}
          refetchComments={refetchComments}
          comments={comments}
        />
      }
    />
  )
}
