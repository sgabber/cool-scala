package it.sga.coolscala.labyrinth

/**
 * Inspired by Bloxorz exercise from the course
 * Functional Program Design in Scala by Martin Odersky (Coursera)
 */
abstract class Game extends Labyrinth {

  type Path = List[Move]


  def from(initial: Stream[(Pos, Path)], alreadySeen: Set[Pos]): Stream[(Pos, Path)] = {
    val newSolutionsIteration = for {
      (position, oldPath) <- initial
      direction <- possibleMoves(position)
      newPos = position towards direction // pun intended
      if !alreadySeen.contains(newPos)
    } yield (newPos, direction +: oldPath)

    // prepending initial otherwise the recursive call would happen anyways and not lazily (I think)
    initial append from(newSolutionsIteration, newSolutionsIteration.map(_._1).toSet ++ alreadySeen)
  }

  def fromInitial: Stream[(Pos, Path)] = from(Stream((start, Nil)), Set(start))

  def solution: List[Move] = fromInitial.filter(_._1 == exit).head._2.reverse
}
