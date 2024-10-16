import { Box, Flex, FlexProps, Heading } from "@chakra-ui/react"
import { PropsWithChildren } from "react"
import PXButton from "./ui/px-button"
import { ICOComment, ICOHeart, ICOSearch } from "../assets"
import PXModal, { usePXModal } from "./ui/px-modal"
import PXTags from "./ui/px-tags"
import PXTag from "./ui/px-tag"
import PXTextarea from "./ui/px-textarea"
import { PXInput, PXInputGroup, PXInputLeftElement } from "./ui/px-input"
import PXIconButton from "./ui/px-icon-button"

interface ComponentSectionProps extends PropsWithChildren {
  title: string
}

function ComponentSection(props: ComponentSectionProps) {
  return (
    <Flex flexDirection="column" gap="32px" as="section" marginTop="32px">
      <Heading as="h2" size="lg">
        {props.title}
      </Heading>
      {props.children}
    </Flex>
  )
}

function HorizontalSection(props: FlexProps) {
  return <Flex alignItems="center" gap="24px" {...props} />
}

export default function ComponentsPage() {
  const { showModal, getModalProps } = usePXModal()

  return (
    <Flex width="100vw" justifyContent="center">
      <Flex
        flexDirection="column"
        width="1200px"
        marginTop="48px"
        marginX="24px"
      >
        <Heading as="h1" fontWeight="bold">
          Components
        </Heading>
        <Box marginTop="18px" borderTop="1px solid" borderTopColor="gray.200" />
        <ComponentSection title="Button">
          <HorizontalSection>
            <PXButton leftIcon={<ICOComment />}>Button</PXButton>
            <PXButton>Button</PXButton>
          </HorizontalSection>
          <HorizontalSection>
            <PXButton leftIcon={<ICOComment />} variant="outlined">
              Button
            </PXButton>
            <PXButton variant="outlined">Button</PXButton>
          </HorizontalSection>
        </ComponentSection>

        <ComponentSection title="Modal">
          <PXButton alignSelf="flex-start" onClick={showModal}>
            Show modal
          </PXButton>

          <PXModal {...getModalProps()} />
        </ComponentSection>

        <ComponentSection title="Chips">
          <PXTags maxWidth="400px">
            <PXTag>All</PXTag>
            <PXTag>AI Art</PXTag>
            <PXTag>Digital Art</PXTag>
            <PXTag>Fantasy</PXTag>
            <PXTag>Cyberpunk</PXTag>
            <PXTag>Retro</PXTag>
          </PXTags>
        </ComponentSection>

        <ComponentSection title="Input">
          <PXInput placeholder="Input" />
          <PXInputGroup>
            <PXInputLeftElement>
              <ICOSearch />
            </PXInputLeftElement>
            <PXInput placeholder="Input" />
          </PXInputGroup>
        </ComponentSection>

        <ComponentSection title="Textarea">
          <PXTextarea placeholder="Input" />
        </ComponentSection>

        <ComponentSection title="Icon Button">
          <PXIconButton aria-label="Like">
            <ICOHeart />
          </PXIconButton>
        </ComponentSection>
      </Flex>
    </Flex>
  )
}
