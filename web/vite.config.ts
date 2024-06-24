/// <reference types="vitest" />

import { defineConfig } from "vite"
import react from "@vitejs/plugin-react"
import tsconfigPaths from "vite-tsconfig-paths"

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react(), tsconfigPaths()],
  server: { port: 3000 },
  test: {
    includeSource: ["src/**/*.{js,ts}"],
    environment: "jsdom",
    globals: true,
    setupFiles: "./src/setup-tests.js",
  },
  // Only for production:
  //
  // define: {
  //   "import.meta.vitest": "undefined",
  // },
})
