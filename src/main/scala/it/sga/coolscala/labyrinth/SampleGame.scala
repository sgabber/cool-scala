package it.sga.coolscala.labyrinth

object SampleGame extends Game {

  def terrain: String =
    """
      |OOOOOOOOOOOOOOOOO
      |O   O           O
      |O O O O OO OOOOOO
      |O O   O  O      E
      |OSOOOOOOOOOOOOOOO
      |""".stripMargin


  def main(args: Array[String]): Unit = {
    println(solution)
  }
}
