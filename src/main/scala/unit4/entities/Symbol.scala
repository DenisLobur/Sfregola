package unit4.entities

sealed trait Symbol {

  protected def beats: List[Symbol]

  def wins(other: Symbol): Boolean =
    beats.contains(other)
}

case object Rock extends Symbol {
  protected val beats: List[Symbol] = List(Lizard, Scissors)
}

case object Paper extends Symbol {
  protected val beats: List[Symbol] = List(Rock, Spock)
}

case object Scissors extends Symbol {
  protected val beats: List[Symbol] = List(Paper, Lizard)
}

case object Lizard extends Symbol {
  protected val beats: List[Symbol] = List(Spock, Paper)
}

case object Spock extends Symbol {
  protected val beats: List[Symbol] = List(Scissors, Rock)
}
