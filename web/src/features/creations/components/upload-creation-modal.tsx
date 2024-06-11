import {
  useToast,
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalCloseButton,
  ModalBody,
  FormControl,
  FormLabel,
  Input,
  Textarea,
  ModalFooter,
  Button,
  Spinner,
} from "@chakra-ui/react"
import { useForm } from "react-hook-form"
import UploadFile from "./upload-file"
import { useUploadCreationMutation } from "../api/creations-api"
import { UploadCreation } from "../api/upload-creation"

interface UploadCreationModalProps {
  onUploadSuccess: () => void
  onClose: () => void
}

export default function UploadCreationModal(props: UploadCreationModalProps) {
  const { register, setValue, handleSubmit } = useForm<UploadCreation>()
  const [uploadCreation, { isLoading }] = useUploadCreationMutation()
  const toast = useToast()

  return (
    <Modal isOpen={true} onClose={props.onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Upload Creation</ModalHeader>
        <ModalCloseButton />
        <ModalBody gap="16px" display="flex" flexDirection="column">
          <FormControl>
            <FormLabel>Title</FormLabel>
            <Input {...register("title")} />
          </FormControl>

          <FormControl>
            <FormLabel>Description</FormLabel>
            <Textarea {...register("description")} />
          </FormControl>

          <UploadFile onUploadSuccess={(file) => setValue("file", file)} />
        </ModalBody>

        <ModalFooter gap="8px" display="flex">
          <Button
            colorScheme="blue"
            type="submit"
            onClick={handleSubmit(async (formData) => {
              await uploadCreation({ userId: 1, uploadCreation: formData })
              toast({
                title: "Success",
                description:
                  "Creation has been uploaded and added to your gallery.",
                status: "success",
                duration: 3000,
                isClosable: true,
              })
              props.onClose()
              props.onUploadSuccess()
            })}
          >
            {isLoading ? <Spinner /> : "OK"}
          </Button>
          <Button variant="ghost" onClick={props.onClose}>
            Cancel
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  )
}
