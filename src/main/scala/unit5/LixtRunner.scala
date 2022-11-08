package unit5

import unit5.Data.*
import unit5.Label.*
import Lixt.*

object LixtRunner {
  @main def run(): Unit = {

    println(Lixt(1, 2, 3))
    println(new ::(1, ::(2, Nil)))
    val contacts = Lixt(
      Contact("Ben", "Ghan", Lixt(ContactNumber("12345", Home), ContactNumber("12345", Work)), None, None),
      Contact("Al", "Gor", Lixt(ContactNumber("2468", Home)), None, None)
    )
    println("Surnames in Lixt " + getSurnames(contacts))
    println("Surnames mapped: " + getSurnamesMap(contacts))
    val ints = Lixt(10, 20, 30, 40)
    println("sum all in Lixt: " + sum(ints))

    val numbers = Lixt(1, 2, 3, 4, 5, 6, 7)
    println("even numbers only: " + filterEvens(numbers))

    println("Plus 5: " + plus5(numbers))

    val contactNumbers = Lixt(ContactNumber("12345", Work), ContactNumber("54321", Work))
    println("Get only numbers " + getNumbers(contactNumbers))

    println("Get contact numbers by flatMap: " + getNumbersFlatMap(contacts))

    println("Triples " + triplesList(List(1,2,3)))

    println("Triples " + triples(Lixt(1,2,3)))

    println("For comprehension triples: " + triplesForComprehensions(Lixt(1,2,3)))

    println(Lixt(1,2))
    println(Lixt(1,2.0))
    println(Lixt.empty[Int])
    //println(Lixt(1.0, 2, 3, 4, 5).toString)

    println("")
    println(Lixt(1, 2, 3).headOption)
    println(Lixt().headOption)
    println(Lixt(1,2,3).apply(2)) // throws IooBException if n >= length
    println("dropping 2 elements: " + Lixt(1,2,3,4,5).drop(2))
    println(Lixt(1,2,3,4,5).drop(6))
    println("finding 3: " + Lixt(1,2,3,4,5).find(_ > 2))

    println("taking 2 elements: " + Lixt(1,2,3,4,5).take(2))

    println("dropWhile < 2: " + Lixt(0, 1, 2).dropWhile(_ < 2))
    println("takeWhile on List < 2: " + List(0, 1, 2).dropWhile(_ < 2))

    println("takeWhile < 2: " + Lixt(0, 1, 2).takeWhile(_ > 2))
    println("takeWhile on List < 2: " + List(0, 1, 2).takeWhile(_ > 2))

    println("filtering elements: " + Lixt(1,1,2,3,4,1,4).filter(_ > 6))
    println("filteringNot elements: " + Lixt(1,1,2,3,4,1,4).filterNot(_ > 2))
  }

  //  export Lixt.*
  def getSurnames(contacts: Lixt[Contact]): Lixt[String] =
    contacts match
      case Nil => Nil
      case head :: tail => head.surname :: getSurnames(tail)

  def getSurnamesMap(contacts: Lixt[Contact]): Lixt[String] =
    contacts.map(_.surname)

  def sum(ints: Lixt[Int]): Int =
    ints match
      case Nil => 0
      case head :: tail => head + sum(tail)

  def filterEvens(numbers: Lixt[Int]): Lixt[Int] =
    numbers match
      case Nil => Nil
      case head :: tail => if (head % 2 == 0) head :: filterEvens(tail) else filterEvens(tail)

  def plus5(Lixt: Lixt[Int]): Lixt[Int] =
    Lixt.map(_ + 5)

  def getNumbers(contacts: Lixt[ContactNumber]): Lixt[String] =
    contacts.map(_.number).flatten

  def getNumbersFlatMap(contacts: Lixt[Contact]): Lixt[ContactNumber] =
    contacts.flatMap(contact => contact.numbers)

  def triples(numbers: Lixt[Int]): Lixt[Int] =
    numbers.flatMap(item => Lixt(item, item, item))

  def triplesList(items: List[Int]): List[Int] =
    items.flatMap(x => List(x,x,x))

  def triplesForComprehensions(items: Lixt[Int]): Lixt[Int] =
    for {
      item <- items
      a <- Lixt(item, item, item)
    } yield a
}
