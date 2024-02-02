import { Box, Image, Text, Flex, Grid, Heading, ChakraProps, FlexProps } from '@chakra-ui/react'
import AuthorSnippet from '@/app/components/AuthorSnippet'

interface ArticleCardProps {
  title: string
  articleExcerpt: string
  author: string
  publishedDate: string
}

function ArticleCard(props: ArticleCardProps) {
  return (
    <Flex
      width='100%'
      border='1px solid'
      borderColor='gray.300'
      flexDirection='column'
      borderRadius='12px'
      overflow='clip'
      _hover={{ borderColor: 'black' }}
    >
      <a href='google.com' style={{ overflow: 'clip' }}>
        <Image src='https://picsum.photos/600/300' transition='transform 0.2s' />

        <Flex flexDirection='column' padding='16px' gap='8px' background='white' overflow='clip'>
          <Heading
            as='h2'
            fontWeight='600'
            fontSize='24px'
            sx={{
              '@media screen and (max-width: 1200px)': {
                fontSize: '20px',
              },
            }}
          >
            {props.title}
          </Heading>

          <Text
            noOfLines={4}
            sx={{
              '@media screen and (max-width: 1200px)': {
                fontSize: '14px',
              },
            }}
          >
            {props.articleExcerpt}
          </Text>

          <AuthorSnippet marginTop="10px" author={props.author} publishedDate={props.publishedDate} />
        </Flex>
      </a>
    </Flex>
  )
}

function FeaturedArticle(props: ArticleCardProps) {
  return (
    <Flex
      gap='32px'
      flexDirection={{ base: 'column', lg: 'row' }}
      sx={{
        '@media screen and (max-width: 1100px)': {
          flexDirection: 'column',
        },
      }}
    >
      <a href='google.com' style={{ overflow: 'clip', borderRadius: '12px' }}>
        <Image
          src='https://picsum.photos/600/300'
          transition='transform 0.2s'
          width={{ base: '100%', lg: undefined }}
        />
      </a>

      <Flex
        flexDirection='column'
        justifyContent='space-evenly'
        maxWidth='560px'
        sx={{
          '@media screen and (max-width: 1100px)': {
            gap: '8px',
            maxWidth: '100%',
          },
        }}
      >
        <a href='google.com'>
          <Heading
            as='h1'
            fontWeight='600'
            fontSize='48px'
            sx={{
              '@media screen and (max-width: 1200px)': {
                fontSize: '28px',
              },
            }}
          >
            {props.title}
          </Heading>
        </a>
        <Text
          noOfLines={4}
          fontSize='20px'
          sx={{
            '@media screen and (max-width: 1200px)': {
              fontSize: '16px',
            },
          }}
        >
          {props.articleExcerpt}
        </Text>

        <AuthorSnippet
          gap='8px'
          alignItems='center'
          marginTop='0px'
          sx={{
            '@media screen and (max-width: 1100px)': {
              marginTop: '10px',
            },
          }}
          author={props.author}
          publishedDate={props.publishedDate}
        />
      </Flex>
    </Flex>
  )
}

const excerpt =
  "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of 'de Finibus Bonorum et Malorum' (The Extremes of Good and Evil) by Cicero"

export default function BlogPage() {
  return (
    <Flex as='main' justifyContent='center' margin='32px'>
      <Flex width='1280px' flexDirection='column' gap='48px'>
        <FeaturedArticle
          title='First Page'
          author='tomdoeslinux'
          publishedDate='Jan 2, 2024'
          articleExcerpt={excerpt}
        />

        <Grid gridTemplateColumns={{ base: 'repeat(1, 1fr)', md: 'repeat(2, 1fr)', lg: 'repeat(3, 1fr)' }} gap='20px'>
          {[...Array(12)].map((num) => (
            <ArticleCard
              key={num}
              title='First Page'
              publishedDate='Jan 2, 2024'
              author='tomdoeslinux'
              articleExcerpt={excerpt}
            />
          ))}
        </Grid>
      </Flex>
    </Flex>
  )
}
