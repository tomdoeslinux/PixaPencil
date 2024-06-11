import { useEffect } from "react"

type NavDirection = "left" | "right"

type OnNavCallback = (direction: NavDirection) => void

export function useArrowKeyNav(onNav: OnNavCallback) {
  useEffect(() => {
    function handleKeyDown(e: KeyboardEvent) {
      if (e.key === "ArrowRight") {
        onNav("right")
      } else if (e.key === "ArrowLeft") {
        onNav("left")
      }
    }

    document.addEventListener("keydown", handleKeyDown)

    return () => document.removeEventListener("keydown", handleKeyDown)
  }, [onNav])
}
