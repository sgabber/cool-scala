package it.sga.coolscala.anagrams

import it.sga.coolscala._
import it.sga.coolscala.primes.PrimesUtils
import org.scalatest.FunSuite


class AnagramsTest extends FunSuite {


  test("testing primes performance and showing timeExec") {
    val iterations = 100

    val primes2 = timeExec {
      PrimesUtils.listPrimes(iterations)
    }
    val primes1 = timeExec {
      PrimesUtils.listPrimesWithSieve(iterations)
    }

    assert(primes1 === primes2)

  }

  test("anagram of tre ") {
    Anagrams.anagram("alto", "./src/main/resources/660000_parole_italiane.txt").map(Anagrams.anagramToString) == Vector("([lo],[at,ta])", "([to],[al,la])", "([alto,lato])")
  }



}
