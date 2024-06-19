package academy.learnprogramming.challenge2

fun main() {

  // 1. Declare a non-nullable float variable two ways,
  // and assign it the value -3847.384
  val version1 = -3847.384F
  val version2 = (-3847.384).toFloat()


  // 2. Now change both of those variable declarations into nullable variables.
  val nullableVersion1: Float = -3847.384F
  val nullalbeVversion2: Float = (-3847.384).toFloat()


  // 3. Now declare an array of type non-nullable Short. Make it size 5,
  // and assign it the values 1, 2, 3, 4, and 5.
  val myShortArray = arrayOf<Short>(1, 2, 3, 4, 5)


  // 4. Now declare an array of nullable Ints and initialize it with the values
  // 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, etc., all the way up to 200.
  var myIntArray = Array<Int?>(40) { i -> (i + 1) * 5 }
  for (x in myIntArray) {
    print("$x ")
  }
  println()

  // 5. You have to call a Java method with the following signature from Kotlin:
  // public void method1(char[] charArray). Declare an array that you could
  // pass to the method and initialize it with the values 'a', 'b', and 'c'.
  val myCharArray = charArrayOf('a', 'b', 'c')


  // 6. Given the following code:
  var x: String? = "I AM IN UPPERCASE"
  // Using one line of code, declare another string variable,
  // and assign it x.toLowerCase() when x isn't null,
  // and the string "I give up!" when x is null.
  val newVariable = x?.lowercase() ?: "I give up!"


  // 7. Now use the let function to (a) lowercase the string, and then
  // (b) replace "am" with "am not" in the result
  val replaced = x.let { it -> it?.lowercase()?.replace("am", "am not") }
  println("replaced: $replaced")


  // 8. You're really, really confident that the variable myNonNullVariable can't
  // contain null.
  // Change the following code to assert that myNonNullVariable isn't null
  // (and shoot yourself in the foot!)

}