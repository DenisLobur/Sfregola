package unit3.org.example.time

import java.time.{ZoneId, ZonedDateTime}
import java.time.format.DateTimeFormatter

class TimePrinter(formatter: DateTimeFormatter) {

  def now(country: String): String = {
    val timeZone = countryToTimezone(country)
    val dateTime = currentDateTime(timeZone)
    dateTimeToString(dateTime)
  }

  private def countryToTimezone(country: String): String = {
    country.toLowerCase match
      case "italy" => "Europe/Rome"
      case "uk" => "Europe/London"
      case "germany" => "Europe/Berlin"
      case "japan" => "Asia/Tokyo"
      case _ => {
        val msg = s"Unknown timezone for country $country"
        //        msg. Server throws 500 if we do not throw exception here
        throw new IllegalArgumentException(msg)
      }
  }

  private def currentDateTime(timeZone: String): ZonedDateTime = {
    val zoneId = ZoneId.of(timeZone)
    ZonedDateTime.now(zoneId)
  }

  private def dateTimeToString(dateTime: ZonedDateTime): String =
    formatter.format(dateTime)

}
