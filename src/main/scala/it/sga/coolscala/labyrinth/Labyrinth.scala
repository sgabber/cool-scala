package it.sga.coolscala.labyrinth

trait Labyrinth {

  def terrain: String

  case class Pos(row: Int, col: Int) {
    def towards(m: Move): Pos = m match {
      case Left => Pos(row, col - 1)
      case Right => Pos(row, col + 1)
      case Up => Pos(row - 1, col)
      case Down => Pos(row + 1, col)
    }

    def isValidMove: Boolean = List(' ','E').contains(getCharAt(this))
  }

  sealed abstract class Move

  case object Left extends Move

  case object Right extends Move

  case object Up extends Move

  case object Down extends Move

  val start: Pos = charPos('S')
  val exit: Pos = charPos('E')

  def field: Vector[Vector[Char]] = terrain.split("\n").toVector.map(_.toVector)

  def charPos(c: Char): Pos = {
    val rowIndex = field.indexWhere(_.contains(c))
    Pos(rowIndex, field(rowIndex).indexOf(c))
  }

  def existsPos(p: Pos): Boolean =
    p.col >= 0 && p.row >= 0 &&
      field.size > p.row && field(p.row).size > p.col


  def getCharAt(x: Pos): Char = if (existsPos(x)) field(x.row)(x.col) else 'O'

  def canMove(from: Pos, direction: Move): Boolean = (from towards direction).isValidMove

  def possibleMoves(p: Pos): List[Move] = List(Up, Left, Down, Right).filter(canMove(p, _))

}
