package it.sga.coolscala.fibonacci

import it.sga.coolscala._

object Fibonacci {
  val BigZero = BigInt(0)
  val BigOne = BigInt(1)


  def fib(n: BigInt): BigInt = n match {
    case BigZero => BigZero
    case BigOne => BigOne
    case _ => fib(n - 1) + fib(n - 2)
  }

  def fibFastList(n: BigInt): List[BigInt] = n match {
    case BigZero => Nil
    case _ => nextFib(fibFastList(n - 1))
  }

  def nextFib(l: List[BigInt]): List[BigInt] = l match {
    case Nil => List(BigZero)
    case _ :: Nil => List(BigOne, BigZero)
    case x :: y :: _ => x + y :: l
  }

  @scala.annotation.tailrec
  def fibTail(n: BigInt, acc: List[BigInt]): List[BigInt] = n match {
    case BigZero => acc
    case _ => fibTail(n - 1, nextFib(acc))
  }

  def fibTail(n: BigInt): List[BigInt] = {
    fibTail(n - 1, List(0))
  }

  def main(args: Array[String]): Unit = {

    val one = timeExec {
      (0 until 40).map(BigInt(_)).map(fib)
    }

    val two = timeExec {
      fibFastList(40).reverse.toVector
    }

    val three = timeExec {
      fibTail(40).reverse.toVector
    }

    println(one)
    println(two)
    println(three)
    assert(one == two)
    assert(one == three)
  }
}
