package unit2

import java.time.format.DateTimeFormatter
import scala.io.StdIn

object TimeApp {
  @main def run(): Unit = {
    val timezone = StdIn.readLine("Give me a timezone: ")
    val timePrinter = new TimePrinter(DateTimeFormatter.RFC_1123_DATE_TIME)
    println(timePrinter.now(timezone))
  }
}
