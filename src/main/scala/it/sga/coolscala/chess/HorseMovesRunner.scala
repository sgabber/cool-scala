package it.sga.coolscala.chess

import it.sga.coolscala.chess.HorseMoves.{Matrix, matrixFill, matrixToString, toInt}

object HorseMovesRunner {

  def main(args: Array[String]): Unit = {
    val matrix: Matrix = Array.ofDim[Int](8, 8)
    matrix(0)(1) = 1
    matrixToString(matrix)

    val moves: Matrix = matrixFill(matrix, 3)
    println(matrixToString(moves))

    val ints = matrix.map(ary => ary.mkString("")).map(toInt)
    ints.foreach(println)
    println(ints.sum)
  }

}
