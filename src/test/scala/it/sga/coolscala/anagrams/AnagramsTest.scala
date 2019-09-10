package it.sga.coolscala.anagrams

import org.scalatest.FunSuite

import scala.annotation.tailrec


class AnagramsTest extends FunSuite {


  test("testing primes performance and showing timeExec") {
    val iterations = 100

    val primes2 = timeExec {
      listPrimes(2, iterations)
    }
    val primes1 = timeExec {
      it.sga.coolscala.anagrams.Anagrams.listPrimesWithSieve(iterations)
    }

    assert(primes1 === primes2)

  }


  def listPrimes(n: BigInt, count: BigInt): List[BigInt] = {
    listPrimes(List(), n, count)
  }

  @tailrec
  final def listPrimes(acc: List[BigInt], n: BigInt, count: BigInt): List[BigInt] = {
    if(count%1000==0) println(".")
    else if(count%100==0) print(".")
    if (count <= 0) acc else {
      val cpl = if (isPrime(n)) (n, count - 1)
      else (BigInt(0), count)
      listPrimes(if (cpl._1 == 0) acc else acc :+ cpl._1, n + 1, cpl._2)
    }
  }

  def isPrime(i: BigInt): Boolean = !(2 to i.toInt / 2).exists(i % _ == 0)
}
