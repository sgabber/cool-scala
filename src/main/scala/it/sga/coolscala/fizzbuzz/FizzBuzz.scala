package it.sga.coolscala.fizzbuzz


object FizzBuzz {

  def main(args: Array[String]): Unit = {

    val nums = (1 to 100).toList

    fizzBuzz(nums).foreach(println)
  }


  def fizzBuzz(nums: List[Int]): List[String] = {
    nums.map(r => {
      val fizz = if (r % 3 == 0) "Fizz" else ""
      val buzz = if (r % 5 == 0) "Buzz" else ""
      if ((fizz + buzz).nonEmpty) fizz + buzz else r.toString
    })
  }
}