import { Box, Flex, FlexProps } from "@chakra-ui/react"
import React, {
  useState,
  isValidElement,
  ReactElement,
  useEffect,
  useRef,
} from "react"
import PXTag, { PXTagProps } from "./px-tag"
import { ICOArrowLeft, ICOArrowRight } from "@/assets"

type ScrollDirection = "left" | "right"

interface ScrollControlProps {
  direction: ScrollDirection
  onClick: () => void
}

function ScrollControl(props: ScrollControlProps) {
  return (
    <Flex
      position="absolute"
      left={props.direction === "left" ? "0" : undefined}
      right={props.direction === "right" ? "0" : undefined}
      top="0"
      bottom="0"
      width="60px"
      background={`linear-gradient(${props.direction === "left" ? "270" : "90"}deg, rgba(0,0,0,0) 0%, rgba(255,255,255,1) 50%, rgba(255,255,255,1) 100%);`}
      alignItems="center"
      onClick={props.onClick}
    >
      <Box
        cursor="pointer"
        marginLeft={props.direction === "right" ? "auto" : undefined}
      >
        {props.direction === "left" ? <ICOArrowLeft /> : <ICOArrowRight />}
      </Box>
    </Flex>
  )
}

interface PXTagsProps extends FlexProps {
  selectable?: boolean
}

export default function PXTags({
  children,
  selectable = true,
  ...props
}: PXTagsProps) {
  const [canScrollLeft, setCanScrollLeft] = useState(false)
  const [canScrollRight, setCanScrollRight] = useState(true)
  const containerRef = useRef<HTMLDivElement | null>(null)

  const [selectedChipIndex, setSelectedChipIndex] = useState<number | null>(
    selectable ? 0 : null,
  )

  useEffect(() => {
    const container = containerRef.current

    if (!container) {
      return
    }

    function updateScrollControls() {
      setCanScrollLeft(container!.scrollLeft > 0)
      setCanScrollRight(
        container!.scrollWidth > container!.scrollLeft + container!.clientWidth,
      )
    }

    updateScrollControls()

    container.addEventListener("scroll", updateScrollControls)

    return () => {
      container.removeEventListener("scroll", updateScrollControls)
    }
  }, [])

  return (
    <Box position="relative" {...props}>
      {canScrollLeft && (
        <ScrollControl
          direction="left"
          onClick={() => {
            containerRef.current?.scrollBy({ left: -200, behavior: "smooth" })
          }}
        />
      )}
      {canScrollRight && (
        <ScrollControl
          direction="right"
          onClick={() => {
            containerRef.current?.scrollBy({ left: 200, behavior: "smooth" })
          }}
        />
      )}
      <Flex
        ref={containerRef}
        gap="8px"
        overflowX="scroll"
        css={{
          "&::-webkit-scrollbar": {
            display: "none",
          },
        }}
        whiteSpace="nowrap"
        alignItems="center"
      >
        {React.Children.map(children, (child, index) => {
          if (isValidElement(child) && child.type === PXTag) {
            return React.cloneElement(child as ReactElement<PXTagProps>, {
              key: index,
              isSelected: selectedChipIndex === index,
              onClick: () => {
                if (selectable) {
                  setSelectedChipIndex(index)
                }
              },
            } satisfies PXTagProps | React.Attributes)
          } else {
            throw new Error(
              "PXTags can only contain PXTag components as children.",
            )
          }
        })}
      </Flex>
    </Box>
  )
}
