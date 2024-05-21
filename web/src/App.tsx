import { useEffect, useRef, useState } from 'react'
import './App.css'
import { Box, Button, Flex, FormControl, FormLabel, Grid, IconButton, Image, Input, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay, Spinner, Text, Textarea, useToast } from '@chakra-ui/react'
import { Creation } from 'models'
import { MdCloudUpload, MdFavorite, MdFavoriteBorder, MdOutlineEdit } from 'react-icons/md'
import { useGetCreationsQuery } from './store/creation/creation'
import { useForm } from 'react-hook-form'
import { UploadCreation } from 'models'
import { useUploadCreationMutation } from './store/user/user'

interface LikeButtonProps {
  onLike: () => void
  onUnlike: () => void 
}

function LikeButton(props: LikeButtonProps) {
  const [isLiked, setIsLiked] = useState(false)

  useEffect(() => {
    if (isLiked) {
      props.onLike() 
    } else {
      props.onUnlike()
    }
  }, [isLiked])

  return (
    <IconButton 
      marginLeft="auto"
      variant="ghost"
      _hover={{}}
      _active={{}} 
      icon={isLiked ? <MdFavorite size={20} /> : <MdFavoriteBorder size={20} />} 
      aria-label="Like" 
      color="white"
      onClick={() => setIsLiked((prevIsLiked) => !prevIsLiked)}
    />
  )
}

interface CreationCardProps {
  creation: Creation
}

function CreationCard(props: CreationCardProps) {
  return (
    <Flex 
      flexDirection="column" 
      borderRadius="xl" 
      overflow="clip" 
      shadow="md"
      width="100%"
      cursor="pointer"
      position="relative"
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

            <LikeButton onLike={() => {}} onUnlike={() => {}} />
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

function App() {
  const [showUploadModal, setShowUploadModal] = useState(false)
  const { data: creations, refetch: refetchCreations } = useGetCreationsQuery()

  if (!creations) {
    return <Text>Loading...</Text>
  }

  return (
    <Flex maxWidth="100vw" alignItems="center" flexDirection="column" marginX="32px">
      <Flex maxWidth="1200px" width="100%" flexDirection="column">
        <Button leftIcon={<MdCloudUpload />} marginLeft="auto" onClick={() => setShowUploadModal(true)}>Upload</Button>

        <Grid 
          templateColumns={{ 
            base: "1fr", 
            sm: "repeat(2, 1fr)", 
            md: "repeat(3, 1fr)", 
            lg: "repeat(4, 1fr)" 
          }} 
          gap="8px" 
        >
          {creations.map((creation, index) => <CreationCard creation={creation} key={index} />)}
        </Grid>
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
