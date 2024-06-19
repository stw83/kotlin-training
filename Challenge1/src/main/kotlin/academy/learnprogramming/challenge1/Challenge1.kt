package academy.learnprogramming.challenge1

fun main() {
  // declare immutable strings with both containing value "Hello"
  val hello1 = "Hello"
  val hello2 = "Hello"


  // Using one line of code, test whether hello1 and hello2 are referentially equal and print the result
  println("$hello1 and $hello2 are equal (referentially): ${hello1 === hello2}")

  // same as above, but do structural equality check
  println("$hello1 and $hello2 are equal (structural): ${hello1 == hello2}")

  // declare a mutable variable of type Int
  var num = 2988

  // Declare an immutable variable of type Any and assign it the string "The Any type is the root of the Kotlin class hierarchy"
  val text: Any = "The Any type is the root of the Kotlin class hierarchy"

  if (text is String){
    println(text.uppercase())
  }


  // Using one line of code, print out the following. Make sure your code is nicely intended
  println("""    1|   11|  111| 1111""".replace('|', '\n'))


}