package it.sga.coolscala.fizzbuzz

import it.sga.coolscala._
import org.scalatest.FunSuite

class FizzBuzzTest extends FunSuite {

  test("fizzBuzz speeds") {
    val one = timeExec {
      FizzBuzz.fizzBuzz((0 to 100).toList)
    }

    val two = timeExec {
      FizzBuzz.fizzBuzzBetter((0 to 100).toList)
    }


    assert(one === two)

  }

}
