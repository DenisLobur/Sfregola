package unit3.org.example.time

import cats.effect.IO
import org.http4s.dsl.Http4sDsl
import org.http4s.{HttpRoutes, Method}

import java.time.format.DateTimeFormatter

class TimeApi extends Http4sDsl[IO] {

  private val printer = new TimePrinter(DateTimeFormatter.RFC_1123_DATE_TIME)

  val service = HttpRoutes.of[IO] {
    case GET -> Root / "datetime" / country => {
      try {
        Ok(printer.now(country))
      } catch {
        case ex: IllegalArgumentException =>
          NotFound(ex.getMessage)
      }
    }
  }
}
