# cool-scala
A collection of code samples that show common problems solved using Scala, mostly in functional fashion.

I made this mostly for fun and for exploring scala capabilities, this code
is not thought as a single big application to run standalone.

The best you can do is to download a good scala ide
(the only one I can think of is IntelliJ)
and then run all the main methods you'll find inside the packages while watching
what the code looks like.

### Anagrams:
A program that, given a string and the path to a dictionary,
computes all the possible anagrams of the string on the dictionary.
It works by converting the words to prime numbers
(each letter is a prime number, the word is the product of the letters) 
and then recursively find all the possible sequences of words 
represented by prime numbers
that multiplied give exactly the input phrase (prime number)
then getting all those bigints  lists and converting them back to words.

#### Anagrams revenge:
A shorter, less efficient but concise program

### Chess:
Common problems on the chessboard.
- **HorseMoves**: after chosing one or more starting cells the algorithm computes all the possible positions of the horse piece after a given number of turns. 

### Fizzbuzz:
Short implementation of the famous fizzbuzz test

### Fibonacci:
Three implementations for the fibonacci sequence.
The first looks exactly like the mathematical definition, it is beautiful but slow.
Then I implemented two more versions that return the full fibonacci numbers' list:
one faster (still recursive) and the last equally faster but tail recursive,
therefore it behaves like an iterative one in terms of heap memory 

### Labyrinth:
A breadth first solver to find a labyrinth's shortest path with three sample labyrinths.
(For labyrinths there are better algorithms I think)