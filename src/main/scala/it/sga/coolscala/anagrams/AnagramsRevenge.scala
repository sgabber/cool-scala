package it.sga.coolscala.anagrams

import scala.io.Source

object AnagramsRevenge {

  val words = "prova le"

  val dict = Source.fromResource("660000_parole_italiane.txt").getLines().toList

  def main(args: Array[String]): Unit = {
    words.permutations.foreach { w: String =>
      if (w.split(" ").forall(dict.contains(_)))
        println(w)
    }
  }


}
