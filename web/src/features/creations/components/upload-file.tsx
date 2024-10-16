import { Flex, Input, Image, IconButton, Text } from "@chakra-ui/react"
import React, { useState, useRef } from "react"
import { useForm } from "react-hook-form"

interface UploadFileProps {
  onUploadSuccess: (file: File) => void
}

export default function UploadFile(props: UploadFileProps) {
  const { register, setValue } = useForm<{ fileList: FileList }>()
  const [fileSrc, setFileSrc] = useState<string | null>(null)
  const fileInputRef = useRef<HTMLInputElement | null>(null)

  function handleFileChange(event: React.ChangeEvent<HTMLInputElement>) {
    const files = event.target.files

    if (files && files.length > 0) {
      const file = files[0]
      console.log("FILE " + file)
      const fileReader = new FileReader()

      fileReader.readAsDataURL(file)

      fileReader.onload = () => {
        setFileSrc(fileReader.result as string)
        props.onUploadSuccess(file)
      }

      setValue("fileList", files)
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
          {/* <MdCloudUpload size={30} /> */}

          <Text textAlign="center" width="100%">
            Drop file here or
            <span
              style={{
                color: "blue",
                fontWeight: "bold",
                cursor: "pointer",
              }}
              onClick={() => fileInputRef.current?.click()}
            >
              &nbsp;upload from computer
            </span>
          </Text>
        </Flex>
      )}

      <Input
        type="file"
        accept="image/png, image/jpeg"
        {...register("fileList")}
        onChange={handleFileChange}
        ref={fileInputRef}
        display="none"
        data-testid="upload-file-input"
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
            // icon={<MdOutlineEdit />}
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
