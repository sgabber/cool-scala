package it.sga.coolscala.labyrinth

/**
 * Inspired by Bloxorz exercise from the course
 * Functional Program Design in Scala by Martin Odersky (Coursera)
 */
abstract class Game extends Labyrinth {

  type Path = List[Move]


  /**
   * Breadth first search that recursively iterates all possible subsequent states
   * Represented as stream to lazily evaluate states until we find a solution.
   *
   * A state is a stream of paths.
   *
   * @param initial     the initial state of the computation
   * @param alreadySeen the positions we have already been to
   *
   * @return the new stream of positions representing a new iteration
   */
  def from(initial: Stream[(Pos, Path)], alreadySeen: Set[Pos]): Stream[(Pos, Path)] = {
    val newSolutionsIteration = for {
      (position, oldPath) <- initial
      direction <- possibleMoves(position)
      newPos = position towards direction // pun intended
      if !alreadySeen.contains(newPos)
    } yield (newPos, direction +: oldPath)

    // pruning for final states different from the solution
    if (newSolutionsIteration.isEmpty)
      initial
    else
    // prepending initial otherwise the recursive call would happen anyways and not lazily (I think)
      initial append from(newSolutionsIteration, newSolutionsIteration.map(_._1).toSet ++ alreadySeen)
  }

  /**
   * We setup the search starting _from_ the initial state
   *
   * @return  a stream that lazily evaluates further steps starting from the initial state
   */
  def fromInitial: Stream[(Pos, Path)] = from(Stream((start, Nil)), Set(start))

  /**
   * @return all the best solutions
   *         eg the ones with the same length as the head solution (=> the one that requires less steps)
   */
  def solution: Stream[Path] = {
    val head = fromInitial.filter(_._1 == exit).head
    fromInitial.filter(posPath => posPath._1 == exit && posPath._2.length == head._2.length)
      .map(_._2.reverse)
  }
}
