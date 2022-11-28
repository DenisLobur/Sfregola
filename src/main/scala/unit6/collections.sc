Set()
Set[Any](1, "scala")
Set.empty[Double]
Set(2) + 1
//1 + Set(2)

def allUpper(set: Set[String]): Set[String] =
  set.map(_.toUpperCase)

val set = Set("brown", "fox", "jumps")

allUpper(set)

Set(Set(3)).flatten

def crossMultiplier(a: Set[Int], b: Set[Int]): Set[Int] =
  a.flatMap(n => b.map(_ * n))

crossMultiplier(Set(1,3), Set(2,4,6))

def crossMultiFor(a: Set[Int], b: Set[Int]): Set[Int] =
  for {
    first <- a
    second <- b
  } yield first * second

crossMultiFor(Set(1,3), Set(2,4,6))