package it.sga.coolscala.primes

import scala.annotation.tailrec

object PrimesUtils {


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

  def listPrimes(count: BigInt): List[BigInt] = listPrimes(List(), 2, count)

  @tailrec
  final def listPrimes(acc: List[BigInt], n: BigInt, count: BigInt): List[BigInt] = {
    if (count <= 0) acc else {
      val cpl = if (isPrime(n)) (n, count - 1)
      else (BigInt(0), count)
      listPrimes(if (cpl._1 == 0) acc else acc :+ cpl._1, n + 1, cpl._2)
    }
  }

  def isPrime(i: BigInt): Boolean = !(2 to i.toInt / 2).exists(i % _ == 0)
}
