package it.sga.coolscala.labyrinth

trait Labyrinth {

  /**
   * Terrain is a string representing a labyrinth.
   * The char ' ' (space) represents a tile of the labyrinth that can be crossed
   * The char 'O' represents a tile that can't be crossed
   * The char 'S' is the starting point
   * The char 'E' is the exit point
   * t
   * @return
   */
  def terrain: String

  /**
   * The terrain as vector of vectors
   * @return
   */
  def field: Vector[Vector[Char]] = terrain.split("\n").toVector.map(_.toVector)

  /**
   * Represents a position in the labyrinth
   * @param row the row of this pos in the labyrinth
   * @param col the col of this pos in the labyrinth
   */
  case class Pos(row: Int, col: Int) {
    /**
     * Returns a new Pos representing the movement of the old one in the input direction
     * @param m the direction
     * @return a new pos
     */
    def towards(m: Move): Pos = m match {
      case Left => Pos(row, col - 1)
      case Right => Pos(row, col + 1)
      case Up => Pos(row - 1, col)
      case Down => Pos(row + 1, col)
    }

    /**
     * Does this pos represent a tile that can be crossed?
     * @return
     */
    def isValidMove: Boolean = List('S',' ','E').contains(getCharAt(this))
  }

  sealed abstract class Move

  case object Left extends Move

  case object Right extends Move

  case object Up extends Move

  case object Down extends Move

  val start: Pos = charPos('S')
  val exit: Pos = charPos('E')

  /**
   * position of the input char in the field
   * @param c the char we are looking for
   * @return the Pos of the char
   */
  def charPos(c: Char): Pos = {
    val rowIndex = field.indexWhere(_.contains(c))
    Pos(rowIndex, field(rowIndex).indexOf(c))
  }

  def existsPos(p: Pos): Boolean =
    p.col >= 0 && p.row >= 0 &&
      field.size > p.row && field(p.row).size > p.col


  /**
   * The char at pos p
   * @param p pos in the field
   * @return char present in p
   */
  def getCharAt(p: Pos): Char = if (existsPos(p)) field(p.row)(p.col) else 'O'

  /**
   *
   * @param staringPos starting pos
   * @param direction pretty obvious
   * @return true if moving from the starting pos towards direction the block is valid
   */
  def canMove(staringPos: Pos, direction: Move): Boolean = (staringPos towards direction).isValidMove

  /**
   * list of all possible moves starting from pos
   * @param p starting pos
   * @return list of possible Move
   */
  def possibleMoves(p: Pos): List[Move] = List(Up, Left, Down, Right).filter(canMove(p, _))

}
