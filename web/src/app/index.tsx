import { Route } from "wouter"
import CreationsPage from "./routes/creations-page"
import CreationDetailsPage from "./routes/creation-details-page"

function AppRouter() {
  return (
    <>
      <Route path="/" component={CreationsPage} />
      <Route path="/creations/:creationId">
        {(params) => (
          <CreationDetailsPage creationId={parseInt(params.creationId)} />
        )}
      </Route>
    </>
  )
}
export default function App() {
  return <AppRouter />
}
