package it.sga.coolscala.anagrams

import scala.io.Source

object AnagramsRevenge {

  val words = "oof arb"

  val dict: List[String] = Source.fromResource("660000_parole_italiane.txt").getLines().toList

  def anagrams(str: String): Iterator[String] = str.permutations.filter(_.split(" ").forall(dict.contains(_)))

  def main(args: Array[String]): Unit = anagrams(words).foreach(println)


}
