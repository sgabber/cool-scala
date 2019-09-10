package it.sga.coolscala

package object anagrams {

  def timeExec[B](f: => B): B = {
    val t0 = System.nanoTime()
    val ret: B = f
    val t1 = System.nanoTime()
    println("elapsed time: " + (t1 - t0) / 1000000000.0 + "s")
    ret
  }

}
