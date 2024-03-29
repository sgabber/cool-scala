package it.sga.coolscala.anagrams

import it.sga.coolscala._

import scala.io.Source

object AnagramsRevenge {

  val words = "nano felicissima"

  val dict: List[String] = Source.fromResource("660000_parole_italiane.txt").getLines().toList

  //version working without spaces problems
  def anagrams(str: String): Iterator[String] = for {
    possibleAnagram <- str.permutations.map(listsFromDict).filter(_.nonEmpty)
    word <- possibleAnagram
  } yield word.mkString(" ")

  def listsFromDict(in: String): List[List[String]] =
    if (in.isEmpty) List(List[String]())
    else for {
      word <- dict
      if in.startsWith(word)
      split <- listsFromDict(in.stripPrefix(word))
    } yield word :: split

  def main(args: Array[String]): Unit =
    timeExec {anagrams(words.toLowerCase().filter(('a' to 'z').contains(_))).take(250).foreach(println)}

}
