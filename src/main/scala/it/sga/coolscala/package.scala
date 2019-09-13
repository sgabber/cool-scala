package it.sga

import java.io.{BufferedWriter, File, FileWriter}

package object coolscala {

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
    println("elapsed time: " + (t1 - t0) / 1000000000.0 + "s")
    ret
  }

  def fileWrite(filename: String, text: Seq[String]): Unit = {
    val writer = new BufferedWriter(new FileWriter(new File(filename)))
    text.foreach(writer.write)
    writer.close()
  }

}
