import { Flex, Text, Button } from "@chakra-ui/react"
import { MdCloudUpload } from "react-icons/md"
import { useState } from "react"
import {
  CreationsGrid,
  UploadCreationModal,
  CreationDetailPopup,
  useGetCreationsQuery,
} from "@/features/creations"
import { Creation } from "@/types/root-types"
import { CommentsList, useGetCommentsQuery } from "@/features/comments"

export default function CreationsPage() {
  const [showUploadModal, setShowUploadModal] = useState(false)
  const [selectedCreation, setSelectedCreation] = useState<Creation | null>(
    null,
  )
  const { data: creationsResponse, refetch: refetchCreations } =
    useGetCreationsQuery()
  const creations = creationsResponse?.content

  const { data: commentsResponse, refetch: refetchComments } =
    useGetCommentsQuery(
      { creationId: selectedCreation?.id ?? 0 },
      { skip: !selectedCreation },
    )
  const comments = commentsResponse?.content

  if (!creations) {
    return <Text>Loading...</Text>
  }

  return (
    <Flex
      maxWidth="100vw"
      alignItems="center"
      flexDirection="column"
      marginX="32px"
    >
      <Flex maxWidth="1200px" width="100%" flexDirection="column">
        <Button
          leftIcon={<MdCloudUpload />}
          marginLeft="auto"
          onClick={() => setShowUploadModal(true)}
          textTransform="uppercase"
          colorScheme="telegram"
          marginTop="32px"
          marginBottom="16px"
        >
          Upload
        </Button>

        <CreationsGrid
          creations={creations}
          onCreationClick={(creation) => setSelectedCreation(creation)}
        />
      </Flex>

      {selectedCreation && (
        <CreationDetailPopup
          creation={selectedCreation}
          commentsSlot={
            <CommentsList
              creationId={selectedCreation.id}
              refetchComments={refetchComments}
              comments={comments}
            />
          }
          onClose={() => setSelectedCreation(null)}
          onNavigation={(direction) => {
            const selectedCreationIndex = creations.indexOf(selectedCreation)

            if (direction === "right") {
              const nextIndex = selectedCreationIndex + 1
              if (nextIndex < creations.length) {
                setSelectedCreation(creations[nextIndex])
              }
            } else if (direction === "left") {
              const prevIndex = selectedCreationIndex - 1
              if (prevIndex >= 0) {
                setSelectedCreation(creations[prevIndex])
              }
            }
          }}
        />
      )}

      {showUploadModal && (
        <UploadCreationModal
          onUploadSuccess={() => refetchCreations()}
          onClose={() => setShowUploadModal(false)}
        />
      )}
    </Flex>
  )
}
