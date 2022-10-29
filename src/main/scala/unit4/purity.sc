
def monitorTemperature(current: Double, recovery: Double): Double = {
  if (current >= 0) current else recovery
}

monitorTemperature(-5, 10)

monitorTemperature(current = 5, recovery = {
  println("Emergency!")
  10
})

def superFlatten(opt: Option[Option[Option[String]]]): Option[String] =
  opt.flatten.flatten

superFlatten(Some(Some(Some("check this out"))))

def f(n: Int): Option[Int] =
  if (n < 5) Some(n * 2)
  else None

def foo(optA: Option[Int]) =
  for {
    a <- optA
    b <- f(a)
    c <- Some(5 * b)
  } yield (a, b, c)

println(foo(Some(1))) // 10
println(foo(Some(5))) // None
println(foo(None)) // None

case class Car(model: String, owner: Option[Person], registrationPlate: Option[String])

case class Person(name: String, age: Int, drivingLicense: Option[String])

def ownerBelowAge(car: Car, age: Int): Option[String] =
  car.owner.flatMap { person =>
    if (person.age < age) Some(person.name)
    else None
  }

def ownerBelowAge2(car: Car, age: Int): Option[String] =
  for {
    person <- car.owner
    if person.age < age
  } yield person.name
