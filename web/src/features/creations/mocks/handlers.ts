import getCreationHandler from "./get-creation-handler"
import getCreationsHandler from "./get-creations-handler"
import uploadCreationHandler from "./upload-creation-handler"

export const creationsHandlers = [
  ...getCreationsHandler,
  ...getCreationHandler,
  ...uploadCreationHandler,
]
