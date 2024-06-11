import React from "react"
import ReactDOM from "react-dom/client"
import "./index.css"
import App from "./app"
import { AppProvider } from "./app/app-provider"

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <AppProvider>
      <App />
    </AppProvider>
  </React.StrictMode>,
)
