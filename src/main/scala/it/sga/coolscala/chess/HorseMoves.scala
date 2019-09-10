package it.sga.coolscala.chess

object HorseMoves {

  type Matrix = Array[Array[Int]]

  def toInt(n: String): Int = toInt(n.reverse, 1)

  def toInt(n: String, base: Int): Int =
    if (n.isEmpty) 0
    else (if (n.head == '1') 1 else 0) * base + toInt(n.tail, base * 2)

  def matrixToString(ary: Matrix): String = ary.map(_.mkString("")).mkString("\n")

  val possibleMoves: List[(Int, Int)] = List((2, 1), (1, 2), (-2, 1), (-1, 2), (2, -1), (1, -2), (-2, -1), (-1, -2))

  def isPossible(x: Int, y: Int): Boolean = 0 <= x && x < 8 && 0 <= y && y < 8

  def getPossibleMoves(x: Int, y: Int): List[(Int, Int)] =
    possibleMoves.map { case (a, b) => (a + x, b + y) }.filter { case (a, b) => isPossible(a, b) }


  def matrixFill(matrix: Matrix, iterations: Int): Matrix = {
    for (_ <- 1 to iterations) {
      val possibleMoves = for {
        x <- 0 to 7
        y <- 0 to 7
        if matrix(x)(y) == 1
      } yield {
        getPossibleMoves(x, y)
      }

      possibleMoves.flatten.foreach { case (x, y) => matrix(x)(y) = 1 }
    }
    matrix
  }


}