val transform: PartialFunction[String, String] = {
  case str if str.startsWith("a") => str.reverse
  case str if str.startsWith("s") => str.toUpperCase
  case default => s"No changes to: $default"
}

transform("abcd")

transform("string")
transform("qwerty")

val f: String => Int = _.size
val g: Int => Boolean = _ > 2

f("one")
g(5)

val gof: String => Boolean = s => g(f(s))
gof("hello")

val b = try {
  "hello".toBoolean
} catch {
  case _: IllegalArgumentException => false
}

b

def parse(s: String): Int = {
  try {
    s.toInt
  } catch {
    case _: Exception => s.length
  }
}

parse("2fg")