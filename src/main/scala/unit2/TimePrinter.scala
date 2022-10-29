package unit2

import java.time.{ZoneId, ZonedDateTime}
import java.time.format.DateTimeFormatter

class TimePrinter(formatter: DateTimeFormatter) {

  def now(timeZone: String): String =
    try {
      val dateTime = currentDateTime(timeZone)
      dateTimeToString(dateTime)
    } catch {
      case _: Exception => s"Invalid timezone: $timeZone"
    }

  private def currentDateTime(timeZone: String): ZonedDateTime =
    val zoneId = ZoneId.of(timeZone)
    ZonedDateTime.now(zoneId)

  private def dateTimeToString(dateTime: ZonedDateTime): String =
    formatter.format(dateTime)

}
