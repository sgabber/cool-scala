package it.sga.coolscala.anagrams

import scala.io.Source
import it.sga.coolscala.primes.PrimesUtils._
import it.sga.coolscala._

object Anagrams {

  /**
   *
   * @param anagram a single anagram
   *
   * @return the string representation of the anagram
   *         each possible word for a single spot is listed in square brackets and basically
   *         the anagram is a list of lists of possible words in round brackets
   */
  def anagramToString(anagram: List[Vector[String]]): String =
    anagram.map(word => word.mkString("[", ",", "]")).mkString("(", ",", ")") + "\n"


  /**
   * Creates the smallest possible list of anagrams from a string
   *
   * @param phrase a phrase in natural language
   *
   * @return a vector of anagrams
   *         structure like this: Anagrams<Anagram<BigintWord<WordInLetters>>>
   *         the vector contains all anagrams, each anagram contains a list of "words" 
   *         but each "word" is actually a vector of words made by the same letters
   */
  def anagram(phrase: String, dictPath: String): Vector[List[Vector[String]]] = {
    val dict = Anagrams.loadDictionary(dictPath)
    val letters = phrase.toLowerCase().filter(('a' to 'z').contains(_))
    val prime = mapToPrime(letters)
    val anagrams = toAnagram(prime, dict.keys.filter(word => prime % word == 0).toVector)
    anagrams.map(_.map(dict)) // converts the list of BigInts to a list of lists of possible words indexed by the number
  }

  /**
   * creates a cartesian product of all the lists of words with the same letters
   * to obtain the final list of anagrams
   *
   * @param singleAnagram a single line of anagrams [ [ba,ab],[po]
   *
   * @return a list with the string representation of all the possible anagrams ["ba po","ab po"]
   */
  def getAnagramCombinations(singleAnagram: List[Vector[String]]): List[String] = {
    singleAnagram.foldLeft(List[String]("")) { (acc, elem) =>
      (acc cross elem).map(x => x._1 + " " + x._2).toList
    }

  }


  /**
   * Recursive function.
   *
   * @param phrase  the phrase to anagram
   * @param subDict a dictionary (list of prime numbers representing words) containing only the words useful for our
   *                phrase
   *
   * @return a vector of anagrams. Each anagram is a list of bigintegers representing words.
   */
  def toAnagram(phrase: BigInt, subDict: Vector[BigInt]): Vector[List[BigInt]] = {
    if (phrase == 1) {
      Vector(List())
    } else {
      val allAttachable = subDict.filter(word => phrase % word == 0).sorted
      for {
        attachableWord <- allAttachable
        singleAnagram <- toAnagram(phrase / attachableWord, allAttachable.filter(_ <= attachableWord))
      } yield attachableWord +: singleAnagram
    }
  }


  /**
   * Each letter is represented by a prime number (see primesMap)
   *
   * Maps a phrase to a prime number by converting each letter to a prime number and multiplying them
   *
   * @param letters the letters composing the phrase
   *
   * @return a prime number representing the phrase
   */
  def mapToPrime(letters: String): BigInt = letters.map(primesMap).product

  /**
   * maps primes to alphabet letters thus {a->2, b->3, c->5,....}
   */
  val primesMap: Map[Char, BigInt] = ('a' to 'z').zip(listPrimes(26)).toMap

  /**
   * Loads dictionary from path on local machine
   *
   * @param path path to dictionary from localmachine
   *
   * @return map of words converted to bigints -> list of words sharing the same bigint conversion
   */
  def loadDictionary(path: String): Map[BigInt, Vector[String]] = {
    val source = Source.fromFile(path)
    val dict = source.getLines().toVector.groupBy(mapToPrime)
    source.close()
    dict
  }
}
