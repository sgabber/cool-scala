package it.sga.coolscala.fibonacci

import it.sga.coolscala.fibonacci.Fibonacci.{fib, fibFastList, fibTail}
import it.sga.coolscala.timeExec
import org.scalatest.FunSuite

class FiboTest extends FunSuite {

  test("main ") {
    val i = 30
    val one = timeExec {
      (0 until i).map(BigInt(_)).map(fib)
    }

    val two = timeExec {
      fibFastList(i).reverse.toVector
    }

    val three = timeExec {
      fibTail(i).reverse.toVector
    }

    println(one)
    println(two)
    println(three)
    assert(one == two)
    assert(one == three)
  }

  test("fib 10") {
    println(fib(10))
  }

  test("more numbers (only the fastest)") {
    println("numbers")
    testTimeNumbers(500)
    testTimeNumbers(2000)

  }

  private def testTimeNumbers(n: Int) = {


    val fibFastResult = timeExec {
      fibFastList(n).reverse.toVector
    }

    println(fibFastResult.size, fibFastResult)

    val fibTailResult = timeExec {
      fibTail(n).reverse.toVector
    }

    println(fibTailResult.size, fibTailResult)

    assert(fibFastResult == fibTailResult)
  }
}

