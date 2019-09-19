package it.sga.coolscala.labyrinth

object ComplexGame2 extends Game {

  def terrain: String = /////////////////////////////////////
    """   O   O   O   O   O   O   O   O   O   O   O   O   O E
      | O O O O O O O O O O O O O O O O O O O O O O O O O O O
      | O O O O O O O O O O O O O O O O O O O O O O O O O O O
      | O O O O O O O O O O O O O O O O O O O O O O O O O O O
      | O O O O O O O O O O O O O O O O O O O O O O O O O O O
      | O O O O O O O O O O O O O O O O O O O O O O O O O O O
      |SO   O   O   O   O   O   O   O   O   O   O   O   O   O
      |""".stripMargin //////////////////////////////////////


  def main(args: Array[String]): Unit = {
    println(solution)
  }
}
