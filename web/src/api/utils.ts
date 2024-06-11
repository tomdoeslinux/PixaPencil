export function formatTimeSince(date: Date): string {
  const now = new Date()
  const seconds = Math.floor((now.getTime() - date.getTime()) / 1000)

  const intervals = [
    { label: "year", seconds: 31536000 },
    { label: "month", seconds: 2592000 },
    { label: "day", seconds: 86400 },
    { label: "hour", seconds: 3600 },
    { label: "minute", seconds: 60 },
  ]

  for (const interval of intervals) {
    const count = seconds / interval.seconds

    if (count >= 1) {
      return `${count} ${interval.label}${count !== 1 ? "s" : ""} ago`
    }
  }

  // If user posted less than 1 minute ago, it will say 'just now'
  return "just now"
}

export function formatDateTime(date: Date): string {
  const options: Intl.DateTimeFormatOptions = {
    weekday: "long",
    year: "numeric",
    month: "long",
    day: "numeric",
    hour: "2-digit",
    minute: "2-digit",
    hour12: true,
  }

  return date.toLocaleString("en-US", options)
}

if (import.meta.vitest) {
  const { describe, expect, test } = import.meta.vitest

  describe("formatTimeSince", () => {
    const now = new Date()

    test("Should return 'just now' for dates less than a minute ago", () => {
      const date = new Date(now.getTime() - 30 * 1000)

      expect(formatTimeSince(date)).toBe("just now")
    })

    test("Should return '1 minute ago' for dates exactly one minute ago", () => {
      const date = new Date(now.getTime() - 60 * 1000)

      expect(formatTimeSince(date)).toBe("1 minute ago")
    })

    test("Should return '5 minutes ago' for dates five minutes ago", () => {
      const date = new Date(now.getTime() - 5 * 60 * 1000)

      expect(formatTimeSince(date)).toBe("5 minutes ago")
    })

    test("Should return '2 hours ago' for dates two hours ago", () => {
      const date = new Date(now.getTime() - 2 * 60 * 60 * 1000)
      expect(formatTimeSince(date)).toBe("2 hours ago")
    })

    test("Should return '1 month ago' for dates exactly one month ago", () => {
      const date = new Date(now.getTime() - 30 * 24 * 60 * 60 * 1000)
      expect(formatTimeSince(date)).toBe("1 month ago")
    })

    test("Should return '1 year ago' for dates exactly one year ago", () => {
      const date = new Date(now.getTime() - 365 * 24 * 60 * 60 * 1000)
      expect(formatTimeSince(date)).toBe("1 year ago")
    })
  })

  describe("formatDateTime", () => {
    test("Should format morning date correctly", () => {
      const date = new Date("2024-06-04T09:15:00") // June 4, 2024, 9:15 AM
      const expected = "Tuesday, June 4, 2024, at 09:15 AM"
      expect(formatDateTime(date)).toBe(expected)
    })

    test("Should format afternoon date correctly", () => {
      const date = new Date("2024-06-04T15:30:00") // June 4, 2024, 3:30 PM
      const expected = "Tuesday, June 4, 2024 at 03:30 PM"
      expect(formatDateTime(date)).toBe(expected)
    })
  })
}
