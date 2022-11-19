package unit5.movies

object PrintResultHelpers {
  def printResult(question: String, answer: String): Unit =
    printResult(question, answers = List(answer))

  def printResult(question: String, answer: Option[String]): Unit =
    printResult(question, answers = answer.toList)

  def printResult(question: String, answers: List[String]): Unit =
    println()
    println("================")
    println(s"$question")
    if (answers.isEmpty) println("NOT FOUND")
    else println(answers.map(a => s"- $a").mkString("\n"))
}
