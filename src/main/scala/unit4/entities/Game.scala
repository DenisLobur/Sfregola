package unit4.entities

case class Game(playerA: Player, playerB: Player) {

  private val winner: Option[Player] =
    (playerA, playerB) match
      case (pA@Player(sA), Player(sB)) if sA.wins(sB) => Some(pA)
      case (Player(sA), pB@Player(sB)) if sB.wins(sA) => Some(pB)
      case _ => None

  val result: String = winner.map(player => s"$player wins!").getOrElse("It's a draw!")
}

object Game {

  def apply(text: String): Game =
    text.split("-", 2) match
      case Array(playerA, playerB) => apply(Player(playerA), Player(playerB))
      case _ =>
        val errMsg = s"Invalid game $text. " + s"Please use the format <name> : <symbol> - <name> : <symbol>"
        throw new IllegalArgumentException(errMsg)
}
