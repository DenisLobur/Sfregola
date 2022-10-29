package unit4

object Runner {
  @main def main(): Unit = {
    println(describePerson(Person("John", Some("Herbert"), "Watson")))
    println(describePerson(Person("John", None, "Watson")))
  }

  private def describePerson(person: Person): String = {
    person.middleName match
      case Some(middle) => s"${person.firstName} $middle ${person.lastName}"
      case None => s"${person.firstName} ${person.lastName}"
  }
}
