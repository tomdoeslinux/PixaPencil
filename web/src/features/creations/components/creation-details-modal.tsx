import { ICOComment, ICOHeart } from "@/assets"
import { PXButton, PXTag, PXTags, PXModal, PXModalProps } from "@/components"
import { Creation } from "@/types/root-types"
import { Image, Flex, Text, Box } from "@chakra-ui/react"
import { ReactNode } from "react"
import { NavDirection, useArrowKeyNav } from "../hooks/use-arrow-key-nav"

// should b pxmodalbaseprops as it inherits flexprops
interface CreationDetailsModalProps extends PXModalProps {
  creation?: Creation
  commentsSlot: ReactNode
  onNavigation: (direction: NavDirection) => void
}

export default function CreationDetailsModal(props: CreationDetailsModalProps) {
  useArrowKeyNav(props.onNavigation)

  return (
    <PXModal isOpen={props.isOpen} onClose={props.onClose}>
      <Flex padding="33px" alignItems="center" width="100%" gap="12px">
        <Image
          width="44px"
          height="44px"
          borderRadius="999px"
          src={props.creation?.author.profilePictureUrl}
        />
        <Box>
          <Text fontSize="20px">{props.creation?.title}</Text>
          <Flex gap="7px">
            <Text fontSize="16px" color="#888888">
              {props.creation?.author.username}
            </Text>
            <Text userSelect="none" color="#888888">
              •
            </Text>
            <Text color="#888888">Follow</Text>
          </Flex>
        </Box>

        <Flex marginLeft="auto" gap="11px">
          <PXButton variant="outlined" leftIcon={<ICOComment />}>
            Comment
            <Box as="span" color="#232323" marginLeft="6px" opacity={0.7}>
              5K
            </Box>
          </PXButton>
          <PXButton leftIcon={<ICOHeart />}>
            Like
            <Box as="span" color="white" marginLeft="6px" opacity={0.7}>
              5K
            </Box>
          </PXButton>
        </Flex>
      </Flex>

      <Image
        alignSelf="center"
        maxHeight="70vh"
        src={props.creation?.imageUrl}
      />

      <Box paddingX="33px" paddingBottom="43px">
        <PXTags marginTop="44px" selectable={false}>
          <PXTag>8-bit art</PXTag>
          <PXTag>Retro Gaming</PXTag>
          <PXTag>Cyberpunk Cityscapes</PXTag>
        </PXTags>

        <Text marginTop="34px">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dolor
          sem, vestibulum non justo at, hendrerit consequat purus. In convallis
          quam eget odio molestie tincidunt. Phasellus ex sapien, luctus nec
          felis et, sollicitudin suscipit turpis. Nullam feugiat non quam nec
          venenatis. Nulla velit augue, tincidunt sit amet malesuada nec, porta
          id risus. Fusce gravida rutrum vulputate. In hac habitasse platea
          dictumst. Proin ornare tristique pulvinar. Nulla imperdiet tortor at
          mattis aliquet. Curabitur condimentum mauris eget quam efficitur, sed
          ornare mauris posuere. In fringilla in velit nec feugiat. Mauris quis
          libero ac arcu eleifend viverra. Integer dapibus leo ut arcu
          consequat, sit amet efficitur erat efficitur. Aenean in imperdiet
          dolor.{" "}
        </Text>
      </Box>

      {props.commentsSlot}
    </PXModal>
  )
}
