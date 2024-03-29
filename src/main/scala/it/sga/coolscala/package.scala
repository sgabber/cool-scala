package it.sga

import java.io.{BufferedWriter, File, FileWriter}

package object coolscala {

  /**
   * thanks to https://stackoverflow.com/questions/14740199/cross-product-in-scala
   *
   * Makes it possible to create a cartesian product between two Traversables
   *
   * @param thisOne other traversable
   * @tparam T type of data contained in traversables
   */
  implicit class Crossable[T](thisOne: Traversable[T]) {
    def cross[T2](that: Traversable[T2]): Traversable[(T, T2)] = for {x <- thisOne; y <- that} yield (x, y)

  }


  /**
   * calculates time of execution. Pass in the function with the parameters like this
   *
   * @param f expression to evaluate
   * @tparam B return type of the expression
   *
   * @return evaluates expression, returns the eval result after printing the timing
   */
  def timeExec[B](f: => B): B = {
    val t0 = System.nanoTime()
    val ret: B = f
    val t1 = System.nanoTime()
    val time = t1 - t0
    if (time / 10000L < 1)
      println("elapsed time: " + time / 1000.0 + "microsecond")
    else if (time / 10000000L < 1)
      println("elapsed time: " + time / 1000000.0 + "ms")
    else
      println("elapsed time: " + time / 1000000000.0 + "s")
    ret
  }

  def fileWrite(filename: String, text: Seq[String]): Unit = {
    val writer = new BufferedWriter(new FileWriter(new File(filename)))
    text.foreach(writer.write)
    writer.close()
  }

}
