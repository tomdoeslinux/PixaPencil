import React, { useEffect, useRef, useState } from 'react'
import './App.css'
import { Box, Button, ButtonProps, Flex, FlexProps, FormControl, FormLabel, Grid, Heading, IconButton, Image, Input, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay, Portal, Spinner, Text, Textarea, Tooltip, useToast } from '@chakra-ui/react'
import { Author, Creation } from 'models'
import { MdChatBubble, MdChatBubbleOutline, MdCloudUpload, MdComment, MdFavorite, MdFavoriteBorder, MdOutlineComment, MdOutlineEdit, MdOutlineVisibility, MdVisibility } from 'react-icons/md'
import { useAddCommentMutation, useDeleteCommentMutation, useGetCreationCommentsQuery, useGetCreationsQuery, useLikeCreationMutation, useUnlikeCreationMutation } from './store/index'
import { useForm } from 'react-hook-form'
import { UploadCreation, Comment } from 'models'
import { useUploadCreationMutation } from './store/index'
import AppModal from './components/AppModal/AppModal'

interface UseLikeButtonProps {
  initialValue: boolean
  onLike: () => void
  onUnlike: () => void
}

interface UseLikeButtonReturn {
  isLiked: boolean
  toggleLike: (e: React.MouseEvent) => void
}

function useLikeButton(props: UseLikeButtonProps): UseLikeButtonReturn {
  const [isLiked, setIsLiked] = useState(props.initialValue)
  const prevIsLiked = useRef(isLiked)

  useEffect(() => {
    if (prevIsLiked.current !== isLiked) {
      if (isLiked) {
        props.onLike()
      } else {
        props.onUnlike()
      }

      prevIsLiked.current = isLiked
    }
  }, [isLiked])

  function toggleLike(e: React.MouseEvent) {
    setIsLiked((prevIsLiked) => !prevIsLiked)
    e.stopPropagation()
  }

  return {
    toggleLike,
    isLiked
  }
}

interface LikeButtonProps extends ButtonProps {
  onLike: () => void
  onUnlike: () => void
  initialValue: boolean
  color?: string
}

function SimpleLikeButton({ onLike, onUnlike, initialValue, color, ...chakraProps }: LikeButtonProps) {
  const { isLiked, toggleLike } = useLikeButton({ 
    initialValue,
    onLike,
    onUnlike
  })

  return (
    <IconButton 
      variant="ghost"
      _hover={{}}
      _active={{}} 
      borderRadius="999px"
      icon={isLiked ? <MdFavorite size={20} /> : <MdFavoriteBorder size={20} />} 
      aria-label="Like" 
      onClick={toggleLike}
      color={color}
      {...chakraProps}
    />
  )
}

function OutlineButton(props: ButtonProps) {
  return (
    <Button 
      borderRadius="lg"
      variant="ghost"
      border="1px solid"
      borderColor="gray.400"
      {...props}
    /> 
  )
}

interface CounterLikeButton extends LikeButtonProps {
  initialLikeCount: number
}

function CounterLikeButton({ onLike, onUnlike, initialValue, color, initialLikeCount, ...chakraProps }: CounterLikeButton) {
  const { isLiked, toggleLike } = useLikeButton({ 
    initialValue,
    onLike: () => { 
      setLikeCount((curLikeCount) => curLikeCount + 1) 
      onLike() 
    },
    onUnlike: () => {
      setLikeCount((curLikeCount) => curLikeCount - 1)
      onUnlike()
    }
  })
  const [likeCount, setLikeCount] = useState(initialLikeCount)

  return (
    <OutlineButton
      leftIcon={isLiked ? <MdFavorite size={20} /> : <MdFavoriteBorder size={20} />} 
      onClick={toggleLike}
      color={color}
      {...chakraProps}
    >
      {likeCount} 
    </OutlineButton>
  )
}

function formatTimeSince(date: Date): string {
  const now = new Date()

  // Diff in seconds
  const seconds = Math.floor((now.getTime() - date.getTime()) / 1000)

  const rtf = new Intl.RelativeTimeFormat('en', { numeric: 'auto' })

  // Calculate difference in years
  let interval = Math.floor(seconds / 31536000)

  if (interval >= 1) {
    return rtf.format(-interval, 'year')
  }

  // Calculate difference in months
  interval = Math.floor(seconds / 2592000)

  if (interval >= 1) {
    return rtf.format(-interval, 'month')
  }

  // Calculate difference in days
  interval = Math.floor(seconds / 86400)

  if (interval >= 1) {
    return rtf.format(-interval, 'day')
  }

  // Calculate difference in hours
  interval = Math.floor(seconds / 3600)

  if (interval >= 1) {
    return rtf.format(-interval, 'hour')
  }

  // Calculate difference in minutes
  interval = Math.floor(seconds / 60)

  if (interval >= 1) {
    return rtf.format(-interval, 'minute')
  }

  return rtf.format(-seconds, 'second')
}

function formatDateTime(date: Date): string {
  const options: Intl.DateTimeFormatOptions = {
    weekday: 'long',
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    hour12: true
  }
  
  return date.toLocaleString('en-US', options)
}

interface AuthorTagProps {
  author: Author
  subheading?: { text: string, tooltip?: string } | string
}

function AuthorTag(props: AuthorTagProps) {
  return (
    <Flex 
      width="100%" 
      alignItems="center" 
      borderBottom="1px solid" 
      borderBottomColor="gray.200"
      padding="12px 16px"
    >
      <Image 
        src={props.author.profilePictureUrl} 
        width="35px"
        height="35px"
        borderRadius="999px"
        marginRight="14px"
      />
      <Box>
        <Text fontWeight="bold">
          {props.author.username}
        </Text>

        {props.subheading && (
          <>
            {typeof props.subheading === 'object' ? (
              <Tooltip label={props.subheading.tooltip} fontSize="14px">
              <Text fontSize="12px">{props.subheading.text}</Text>
            </Tooltip>
            ) : (
              <Text fontSize="12px">{props.subheading}</Text>
            )}
          </>
        )}
      </Box>
    </Flex>
  )
}

interface CommentItemProps {
  comment: Comment
  onActionSuccess: () => void
}

function CommentItem(props: CommentItemProps) {
  const [deleteComment] = useDeleteCommentMutation()

  return (
    <Flex>
      <Text>{props.comment.text}</Text>
      <Button onClick={async () => {
        await deleteComment(props.comment.id)
        props.onActionSuccess()
      }}>Delete</Button>
    </Flex>
  )
}

interface CreationDetailsModalProps {
  creation: Creation
  onClose: () => void
}

function CreationDetailsModal(props: CreationDetailsModalProps) {
  const [likeCreation] = useLikeCreationMutation()
  const [unlikeCreation] = useUnlikeCreationMutation()

  const uploadDate = new Date(props.creation.createdAt)
  
  const timeSince = formatTimeSince(uploadDate)
  const formattedDateTime = formatDateTime(uploadDate)

  const { data: comments, refetch: refetchComments } = useGetCreationCommentsQuery(props.creation.id)
  const [addComment] = useAddCommentMutation()
  const { register, getValues } = useForm<{ text: string }>()

  return (
    <AppModal onClose={props.onClose} display="flex">
      <Image 
        background="black" 
        maxWidth="700px" 
        maxHeight="600px"
        objectFit="contain" 
        src={props.creation.imageUrl} 
      />

      <Flex flexDirection="column" width="400px">
        <AuthorTag 
          author={props.creation.author} 
          subheading={{ text: timeSince, tooltip: formattedDateTime }} 
        />

        <Box paddingLeft="16px" borderTop="1px solid" borderTopColor="gray.300">
          <Heading as="h1" size="lg" marginTop="16px">{props.creation.title}</Heading>
          <Text marginTop="12px">{props.creation.description}</Text>
        </Box>

        <Flex padding="12px 16px" marginTop="8px" gap="8px">
          <CounterLikeButton
            key={props.creation.id}
            onLike={() => likeCreation({ creationId: props.creation.id, userId: 1 })} 
            onUnlike={() => unlikeCreation({ creationId: props.creation.id, userId: 1 })} 
            color="black"
            initialValue={props.creation.isLiked}
            width="100%"
            initialLikeCount={props.creation.likeCount}
          />
          <OutlineButton leftIcon={<MdChatBubbleOutline />} width="100%">
            0
          </OutlineButton>
          <OutlineButton leftIcon={<MdOutlineVisibility />} width="100%">
            0
          </OutlineButton>
        </Flex>

        <Flex padding="12px 16px" flexDirection="column" flexGrow={1} background="gray.100">
          <Text fontWeight="bold">12 Comments</Text>
          {comments?.map((comment) => (
            <CommentItem 
              key={comment.id} 
              onActionSuccess={() => refetchComments()} 
              comment={comment} 
            />
          ))}
          <Input marginTop="auto" placeholder="Add a comment" {...register('text')} />
          <Button onClick={async () => {
            await addComment({ userId: 1, creationId: props.creation.id, text: getValues('text') })
            refetchComments()
          }}>OK</Button>
        </Flex>
      </Flex>
    </AppModal>
  )
}

interface CreationCardProps {
  creation: Creation
  onClick: () => void
}

function CreationCard(props: CreationCardProps) {
  const [likeCreation] = useLikeCreationMutation()
  const [unlikeCreation] = useUnlikeCreationMutation()

  return (
    <Flex 
      flexDirection="column" 
      borderRadius="xl" 
      overflow="clip" 
      shadow="md"
      width="100%"
      cursor="pointer"
      position="relative"
      onClick={props.onClick}
    >
      <Image 
        src={props.creation.imageUrl} 
        objectFit="cover" 
        aspectRatio="1 / 1" 
        width="100%" 
      />

      <Flex 
        position="absolute" 
        width="100%" 
        height="100%" 
        background="linear-gradient(to bottom, rgba(0, 0, 0, 0) 30%, rgba(0, 0, 0, 0.8) 100%)" 
        opacity="0"
        transition="opacity 0.1s"
        _hover={{ opacity: 1 }}
        padding="16px"
        bottom="0"
      >
        <Box marginTop="auto" width="100%">
          <Text 
            fontWeight="bold" 
            color="white" 
            fontSize="lg"
            marginBottom="8px"
          >{props.creation.title}</Text>

          <Flex gap="8px" alignItems="center" width="100%">
            <Image 
              borderRadius="999px" 
              src={props.creation.author.profilePictureUrl} 
              width="30px" 
              height="30px" 
            />

            <Text color="white">{props.creation.author.username}</Text>

            <SimpleLikeButton 
              onLike={() => likeCreation({ creationId: props.creation.id, userId: 1 })} 
              onUnlike={() => unlikeCreation({ creationId: props.creation.id, userId: 1 })} 
              initialValue={props.creation.isLiked}
              color="white"
              marginLeft="auto"
            />
          </Flex>
        </Box>
      </Flex>
    </Flex>
  )
}

interface UploadFileProps {
  onUploadSuccess: (file: File) => void
}

function UploadFile(props: UploadFileProps) {
  const { register, setValue } = useForm<{ fileList: FileList }>()
  const [fileSrc, setFileSrc] = useState<string | null>(null)
  const fileInputRef = useRef<HTMLInputElement | null>(null)

  function handleFileChange(event: React.ChangeEvent<HTMLInputElement>) {
    const files = event.target.files

    if (files && files.length > 0) {
      const file = files[0]
      const fileReader = new FileReader()

      fileReader.readAsDataURL(file)

      fileReader.onload = () => {
        setFileSrc(fileReader.result as string);
        props.onUploadSuccess(file)
      }

      setValue('fileList', files)
    }
  }

  return (
    <>
      {!fileSrc && (
        <Flex 
          border="4px dashed"
          borderColor="gray.200"
          flexDirection="column" 
          width="100%" 
          height="150px" 
          borderRadius="lg"
          alignItems="center"
          justifyContent="center"
          gap="8px"
        >
          <MdCloudUpload size={30} />

          <Text textAlign="center" width="100%">
            Drop file here or 
            <span 
              style={{ 
                color: 'blue', 
                fontWeight: 'bold', 
                cursor: 'pointer',
              }}
              onClick={() => fileInputRef.current?.click()}
            >&nbsp;upload from computer</span>
          </Text>
        </Flex>
      )}
      
      <Input
        type="file" 
        accept="image/png, image/jpeg"
        {...register('fileList')}
        onChange={handleFileChange}
        ref={fileInputRef}
        display="none"
      />

      {fileSrc && (
        <Flex position="relative">
          <Image src={fileSrc} borderRadius="lg" />
          <IconButton 
            onClick={() => fileInputRef.current?.click()}
            position="absolute"
            variant="outline" 
            background="white"
            right="0" 
            icon={<MdOutlineEdit />} 
            aria-label="Replace file" 
            borderRadius="999px"
            margin="8px"
            shadow="md"
          />
        </Flex>
      )}
    </>
  )
}

interface UploadCreationModalProps {
  onUploadSuccess: () => void
  onClose: () => void
}

function UploadCreationModal(props: UploadCreationModalProps) {
  const { register, setValue, handleSubmit } = useForm<UploadCreation>()
  const [uploadCreation, { isLoading }] = useUploadCreationMutation()
  const toast = useToast()

  return (
    <Modal isOpen={true} onClose={props.onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Upload Creation</ModalHeader>
        <ModalCloseButton />
        <ModalBody 
          gap="16px" 
          display="flex" 
          flexDirection="column"
        >
          <FormControl>
            <FormLabel>Title</FormLabel>
            <Input {...register('title')} />
          </FormControl>

          <FormControl>
            <FormLabel>Description</FormLabel>
            <Textarea {...register('description')} />
          </FormControl>

          <UploadFile onUploadSuccess={(file) => setValue('file', file)} />
        </ModalBody>

        <ModalFooter gap="8px" display="flex">
          <Button 
            colorScheme='blue' 
            type="submit" 
            onClick={handleSubmit(async (formData) => {
              await uploadCreation({ userId: 1, uploadCreation: formData })
              toast({
                title: 'Success',
                description: "Creation has been uploaded and added to your gallery.",
                status: 'success',
                duration: 3000,
                isClosable: true,
              })
              props.onClose()
              props.onUploadSuccess()
            })}
          >
            {isLoading ? <Spinner /> : "OK"}
          </Button>
          <Button variant='ghost' onClick={props.onClose}>Cancel</Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  )
}

interface CreationsGridProps {
  creations: Creation[]
}

function CreationsGrid(props: CreationsGridProps) {
  const [selectedCreation, setSelectedCreation] = useState<Creation | null>(null)

  return (
    <Grid 
      templateColumns={{ 
        base: "1fr", 
        sm: "repeat(2, 1fr)", 
        md: "repeat(3, 1fr)", 
        lg: "repeat(4, 1fr)" 
      }} 
      gap="8px"
    >
      {props.creations.map((creation) => (
        <CreationCard 
          creation={creation} 
          key={creation.id} 
          onClick={() => setSelectedCreation(creation)} 
        />
      ))}

      {selectedCreation && (
        <CreationDetailsModal 
          creation={selectedCreation}
          onClose={() => setSelectedCreation(null)} 
        />
      )}
    </Grid>
  )
}

function App() {
  const [showUploadModal, setShowUploadModal] = useState(false)
  const { data: creations, refetch: refetchCreations } = useGetCreationsQuery()

  if (!creations) {
    return <Text>Loading...</Text>
  }

  return (
    <Flex maxWidth="100vw" alignItems="center" flexDirection="column" marginX="32px">
      <Flex maxWidth="1200px" width="100%" flexDirection="column">
        <Button 
          leftIcon={<MdCloudUpload />} 
          marginLeft="auto" 
          onClick={() => setShowUploadModal(true)}
          textTransform="uppercase"
          colorScheme="telegram"
          marginTop="32px"
          marginBottom="16px"
        >Upload</Button>

        <CreationsGrid creations={creations} />
      </Flex>

      {showUploadModal && (
        <UploadCreationModal 
          onUploadSuccess={() => refetchCreations()}
          onClose={() => setShowUploadModal(false)}
        />
      )}
    </Flex>
  )
}

export default App
