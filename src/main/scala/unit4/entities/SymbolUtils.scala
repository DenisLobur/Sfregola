package unit4.entities

object SymbolUtils {

  def fromString(text: String): Symbol =
    text.trim.toLowerCase match {
      case "rock" => Rock
      case "paper" => Paper
      case "scissors" => Scissors
      case "lizard" => Lizard
      case "spock" => Spock
      case unknown =>
        val errMsg = s"Unknown symbol $unknown. " + "Please pick a valid symbol [Rock, Paper, Scissors, Lizard, Spock]"
        throw new IllegalArgumentException(errMsg)
    }
}
