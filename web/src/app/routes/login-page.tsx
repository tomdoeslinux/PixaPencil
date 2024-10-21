import { LoginForm } from "@/features/auth"
import { useGetDailyCreationQuery } from "@/features/creations"

export default function LoginPage() {
  const { data: dailyCreation } = useGetDailyCreationQuery()

  return dailyCreation && <LoginForm dailyCreation={dailyCreation} />
}
