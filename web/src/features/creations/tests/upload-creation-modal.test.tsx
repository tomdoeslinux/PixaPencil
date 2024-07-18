import UploadCreationModal from "../components/upload-creation-modal"
import {expect, vi} from "vitest"
import {fireEvent, render, screen, waitFor} from "@testing-library/react"
import { AppProvider } from "src/app/app-provider"

function mockFile(): File {
  const file = new File(["data:image/png;base64"], "mock.jpg", {
    type: "image/jpeg",
  })
  Object.defineProperty(file, "size", { value: 1000 * 1000 + 1 })

  return file
}

describe("Tests for upload creation modal", () => {
  const onClose = vi.fn()
  const onUploadSuccess = vi.fn()

  it("renders the modal and interacts with it", async () => {
    render(
      <UploadCreationModal
        onClose={onClose}
        onUploadSuccess={onUploadSuccess}
      />,
      { wrapper: AppProvider },
    )

    expect(screen.getByText("Upload Creation")).toBeInTheDocument()
    expect(screen.getByLabelText("Title")).toBeInTheDocument()
    expect(screen.getByLabelText("Description")).toBeInTheDocument()
    expect(screen.getByText("Cancel")).toBeInTheDocument()
    expect(screen.getByText("OK")).toBeInTheDocument()
    expect(screen.getByTestId("upload-file-input")).toBeInTheDocument()

    fireEvent.change(screen.getByLabelText("Title"), {
      target: { value: "Test Title" },
    })
    fireEvent.change(screen.getByLabelText("Description"), {
      target: { value: "Test Description" },
    })

    const uploadFileInput = screen.getByTestId("upload-file-input")
    fireEvent.change(uploadFileInput, {
      target: { file: mockFile() },
    })

    fireEvent.click(screen.getByText("OK"))

    await waitFor(() => {
      expect(
        screen.getByText(
          "Creation has been uploaded and added to your gallery.",
        ),
      ).toBeInTheDocument()
    })

    expect(onClose).toHaveBeenCalled()
    expect(onUploadSuccess).toHaveBeenCalled()
  })
})
