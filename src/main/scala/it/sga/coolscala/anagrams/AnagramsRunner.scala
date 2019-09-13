package it.sga.coolscala.anagrams

import it.sga.coolscala.anagrams.Anagrams.{anagram, anagramToString}
import it.sga.coolscala._

object AnagramsRunner {
  def main(args: Array[String]): Unit = {
    timeExec {
      val value = args.mkString("").filter(('a' to 'z').contains(_))
      val out = anagram(value, "./src/main/resources/660000_parole_italiane.txt").filterNot(anagram => anagram.count(_.head.length < 3) > 2)

      val lines = out.map(anagramToString)

      lines.take(10).foreach(println)
      println(lines.size)
      //fileWrite("/tmp/ans.txt", lines.map(_ + "\n"))
    }
  }

}
