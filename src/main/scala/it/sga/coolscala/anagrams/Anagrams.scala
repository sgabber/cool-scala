package it.sga.coolscala.anagrams

import scala.io.Source


object Anagrams {

  /**
   *
   * @param anagram a single anagram
   *
   * @return the string representation of the anagram
   *         each possible word for a single spot is listed in square brackets and basically
   *         the anagram is a list of lists of possible words in round brackets
   */
  def anagramToString(anagram: List[Vector[String]]): String = anagram.map(word => word.mkString("[", ",", "]")).mkString("(", ",", ")")


  /**
   * Creates the smallest possible list of anagrams from a string
   *
   * @param phrase a phrase in natural language
   *
   * @return a vector of anagrams
   *         each list contains a single anagram
   *         each vector inside the list contains a list of words made by the same letters
   */
  def anagram(phrase: String): Vector[List[Vector[String]]] = {
    val letters = phrase.toLowerCase().filter(('a' to 'z').contains(_))
    val prime = mapToPrime(letters)
    val anagrams = toAnagram(prime, dict.keys.filter(word => prime % word == 0).toVector)
    anagrams.map(_.map(dict)) // converts the list of BigInts to a list of lists of possible words indexed by the number
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
   * maps primes to alphabet letters
   */
  val primesMap: Map[Char, BigInt] = ('a' to 'z').zip(listPrimes(2, 26)).toMap

  /**
   * Each letter is represented by a prime number (see primesMap)
   *
   * Maps a phrase to a prime number by converting each letter to a prime number and multiplying them
   *
   * @param letters the letter composing the phrase
   *
   * @return a prime number representing the phrase
   */
  def mapToPrime(letters: String): BigInt = letters.map(primesMap).product

  def listPrimes(n: BigInt, count: BigInt): List[BigInt] = {
    if (count == 0) List()
    else if (isPrime(n)) n :: listPrimes(n + 1, count - 1)
    else listPrimes(n + 1, count)
  }

  def isPrime(i: BigInt): Boolean = !(2 to i.toInt / 2).exists(i % _ == 0)

  val dict: Map[BigInt, Vector[String]] = Source.fromResource("660000_parole_italiane.txt")
    .getLines().toVector.groupBy(mapToPrime)
}
