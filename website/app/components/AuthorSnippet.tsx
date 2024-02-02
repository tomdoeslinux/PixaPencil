import { Flex, FlexProps, Image, Text } from '@chakra-ui/react'

interface AuthorSnippetProps extends FlexProps {
  author: string
  publishedDate: string
}

export default function AuthorSnippet({ author, publishedDate, ...props }: AuthorSnippetProps) {
  return (
    <Flex gap='8px' alignItems='center' {...props}>
      <Image height='30px' width='30px' src='/author.png' borderRadius='999px' background='gray' />

      <Flex flexDirection='column'>
        <Text fontSize='12px' fontWeight='normal'>
          {author}
        </Text>
        <Text as='time' fontSize='12px' fontWeight='normal'>
          {publishedDate}
        </Text>
      </Flex>
    </Flex>
  )
}
