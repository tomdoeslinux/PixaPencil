import { creationsHandlers } from "@/features/creations"
import { setupServer } from "msw/node"

const rootHandlers = [...creationsHandlers]

export const server = setupServer(...rootHandlers)
