import { Flex, Text } from "@chakra-ui/react"
import { useState } from "react"
import {
  CreationsGrid,
  CreationDetailsModal,
  useGetCreationsQuery,
  TopBanner,
} from "@/features/creations"
import { Creation } from "@/types/root-types"
import { CommentsList, useGetCommentsQuery } from "@/features/comments"
import { PXTag, PXTags, usePXModal } from "@/components"
import { PXLayout, PXLayoutContent } from "src/components/ui/px-layout"

export default function CreationsPage() {
  const {
    showModal: showCreationDetailsModal,
    getModalProps: getCreationDetailsModalProps,
  } = usePXModal()
  const [selectedCreation, setSelectedCreation] = useState<
    Creation | undefined
  >(undefined)
  const { data: creationsResponse, refetch: refetchCreations } =
    useGetCreationsQuery(0)
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
    // <Flex maxWidth="100vw" flexDirection="column">
    //   <TopBanner />

    //   <Flex
    //     maxWidth="1200px"
    //     width="100%"
    //     flexDirection="column"
    //     alignSelf="center"
    //     paddingTop="48px"
    //     marginX="16px"
    //   >
    //     <Flex
    //       maxWidth="1200px"
    //       width="100%"
    //       flexDirection="column"
    //       alignSelf="center"
    //       paddingTop="48px"
    //       marginX="16px"
    //     >
    //       <PXTags>
    //         <PXTag>Retro</PXTag>
    //         <PXTag>Vintage</PXTag>
    //         <PXTag>Modern</PXTag>
    //         <PXTag>Classic</PXTag>
    //         <PXTag>Futuristic</PXTag>
    //         <PXTag>Eco</PXTag>
    //         <PXTag>Minimalist</PXTag>
    //         <PXTag>Art Deco</PXTag>
    //         <PXTag>Baroque</PXTag>
    //         <PXTag>Gothic</PXTag>
    //         <PXTag>Victorian</PXTag>
    //         <PXTag>Contemporary</PXTag>
    //         <PXTag>Industrial</PXTag>
    //         <PXTag>Rustic</PXTag>
    //         <PXTag>Scandinavian</PXTag>
    //         <PXTag>Mediterranean</PXTag>
    //         <PXTag>Bohemian</PXTag>
    //         <PXTag>Geometric</PXTag>
    //         <PXTag>Nautical</PXTag>
    //         <PXTag>Zen</PXTag>
    //         <PXTag>Hi-Tech</PXTag>
    //       </PXTags>
    //     </Flex>

    //     <CreationsGrid
    //       creations={creations}
    //       onCreationClick={(creation) => {
    //         setSelectedCreation(creation)
    //         showCreationDetailsModal()
    //       }}
    //     />
    //   </Flex>

    //   <CreationDetailsModal
    //     {...getCreationDetailsModalProps({
    //       onClose: () => setSelectedCreation(undefined),
    //     })}
    //     creation={selectedCreation}
    //     commentsSlot={<CommentsList creation={selectedCreation!} />}
    //     // onNavigation={(direction) => {
    //     //   const selectedCreationIndex = creations.indexOf(selectedCreation)

    //     //   if (direction === "right") {
    //     //     const nextIndex = selectedCreationIndex + 1
    //     //     if (nextIndex < creations.length) {
    //     //       setSelectedCreation(creations[nextIndex])
    //     //     }
    //     //   } else if (direction === "left") {
    //     //     const prevIndex = selectedCreationIndex - 1
    //     //     if (prevIndex >= 0) {
    //     //       setSelectedCreation(creations[prevIndex])
    //     //     }
    //     //   }
    //     // }}
    //   />
    // </Flex>
    <Flex maxWidth="100vw" flexDirection="column">
      <TopBanner />

      <PXLayout>
        <PXLayoutContent>
          <CreationsGrid
            creations={creations}
            onCreationClick={(creation) => {
              setSelectedCreation(creation)
              showCreationDetailsModal()
            }}
          />
        </PXLayoutContent>
      </PXLayout>
    </Flex>
  )
}
