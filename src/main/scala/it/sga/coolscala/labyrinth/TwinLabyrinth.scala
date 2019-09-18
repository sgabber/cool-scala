package it.sga.coolscala.labyrinth

object TwinLabyrinth extends Game {

  def terrain: String =
    """
      |O               O
      |S OOOOOOOOOOOO EO
      |O               O
      |""".stripMargin

  /**
   * in this case there are four equally good solutions
   *
   */
  def main(args: Array[String]): Unit = {
    solution.take(5).toList.foreach(println)
  }
}
