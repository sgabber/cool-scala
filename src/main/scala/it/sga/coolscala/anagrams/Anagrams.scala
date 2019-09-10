package it.sga.coolscala.anagrams

import scala.annotation.tailrec
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
  val primesMap: Map[Char, BigInt] = ('a' to 'z').zip(listPrimesWithSieve(26)).toMap

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

  /**
   * representation of all natural numbers starting from x
   *
   * @param x beginning of the stream of natural numbers
   *
   * @return a stream starting with x
   */
  def next(x: BigInt): Stream[BigInt] = x #:: next(x + 1)

  /**
   * algorithm that computes all the primes
   *
   * @param in stream of natural numbers
   *
   * @return the same input list containing only primes
   */
  def sieve(in: Stream[BigInt]): Stream[BigInt] = in.head #:: sieve(in.tail.filter(_ % in.head != 0))

  /**
   * starting from 2 gets all the primes in the sieve stream
   *
   * @param count the number of primes required
   *
   * @return a list of the first *count* primes
   */
  def listPrimesWithSieve(count: BigInt): List[BigInt] = sieve(next(2)).take(count.toInt).toList

  val dict: Map[BigInt, Vector[String]] = Source.fromResource("660000_parole_italiane.txt")
    .getLines().toVector.groupBy(mapToPrime)
}
