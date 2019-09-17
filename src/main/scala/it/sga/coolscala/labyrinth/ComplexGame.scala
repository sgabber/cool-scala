package it.sga.coolscala.labyrinth

object ComplexGame extends Game {

  def terrain: String = /////////////////////////////////////
    """                               OOOOOOOOO             O
      |OOOOOOOOOOOOOOOOOO                      O  OOOOOOOOO O
      |O   O           O            O          O  OE      O O
      |O O O O OO OOOOOO   O    O   O          O  OOOOO   O O
      |O O   O  O        O  OO      O          O          O O
      |OSOOOOOOOOOOOOOOO   O        O          OOOOOOOOOOOO O
      |                   O                                 O
      |""".stripMargin //////////////////////////////////////


  def main(args: Array[String]): Unit = {
    println(solution)
  }
}
