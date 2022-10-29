package unit4.server

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import unit4.entities.Game

class GameApi extends Http4sDsl[IO] {
  val routes = HttpRoutes.of[IO] {
    case req@POST -> Root / "play" =>
      for {
        text <- req.as[String]
        response <- Ok(Game(text).result)
      } yield response
  }
}
