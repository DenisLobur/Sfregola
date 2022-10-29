package unit3.org.example.ping

import cats.effect.IO
import org.http4s.{HttpRoutes, Method}
import org.http4s.dsl.Http4sDsl

class PingApi extends Http4sDsl[IO] {

  //TODO: find out how to print any path
  val routes = HttpRoutes.of[IO] {
    case GET -> Root / "ping" => Ok("pong")
    case GET -> Root / "ping" / name => Ok(s"pong $name")
    case x -> Root / path => Ok(s"method is ${x.toString}; path is $path")
  }
}
