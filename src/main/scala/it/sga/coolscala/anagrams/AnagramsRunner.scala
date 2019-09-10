package it.sga.coolscala.anagrams

import it.sga.coolscala.anagrams.Anagrams.{anagram, anagramToString}

object AnagramsRunner {
  def main(args: Array[String]): Unit = {
    timeExec {
      val value = "sebastiano collera".filter(('a' to 'z').contains(_))
      println(value)
      val out = anagram(value).filterNot(anagram => anagram.count(_.head.length < 3) > 2)

      val lines = out.map(anagramToString)

      lines.take(10).foreach(println)
      //fileWrite("/tmp/ans.txt", lines.map(_ + "\n"))
    }
  }

}
