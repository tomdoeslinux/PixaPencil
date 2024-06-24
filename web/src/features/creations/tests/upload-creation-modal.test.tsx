import UploadCreationModal from "../components/upload-creation-modal"
import { vi } from "vitest"
import { render, screen } from "@testing-library/react"
import { AppProvider } from "src/app/app-provider"

describe("Tests for upload creation modal", () => {
  const onCloseMock = vi.fn()
  const onUploadSuccessMock = vi.fn()

  it("renders correctly", () => {
    render(
      <AppProvider>
        <UploadCreationModal
          onClose={onCloseMock}
          onUploadSuccess={onUploadSuccessMock}
        />
      </AppProvider>,
    )

    expect(screen.getByText("Upload Creation")).toBeInTheDocument()
  })
})
