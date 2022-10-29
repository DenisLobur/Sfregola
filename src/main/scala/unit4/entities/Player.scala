package unit4.entities

class Player(name: String, val symbol: Symbol) {
  override def toString: String = s"Player $name with symbol $symbol"
}

object Player {

  def apply(text: String): Player =
    text.split(":", 2) match {
      case Array(name, symbol) =>
        new Player(name.trim, SymbolUtils.fromString(symbol))
      case _ =>
        val errMsg = s"Invalid Player $text. " + "Please use format <name> : <symbol>"
        throw new IllegalArgumentException(errMsg)
    }

  def unapply(player: Player): Option[Symbol] =
    Some(player.symbol)
}
