package academy.learnprogramming.section6challenge

import kotlin.random.Random

fun main() {
//  first()
//  second()
//  third()
//  fourth()
  fifth()
  fifthSecond()
//  sixth()
//  seventh()


}

fun seventh() {
  TODO("Not yet implemented")
}

fun sixth() {
  TODO("Not yet implemented")
}

fun fifthSecond(){
  // declare a variable called num (int) and assign it whatever you want
  // declare a variable dnum (double) and assign it as follows:
  // if num > 100, assign dnum -234.567
  // if num < 100, assign dnum 444.555
  // if num == 100, assign dnum 0.0
  val num = Random((Math.random()*100).toInt()).nextInt()
  println("num is: $num")

  val dnum = when {
    num < 100-> 4444.5555
    num == 100 -> 0.0
    else -> -234.567
  }

  println("dnum is $dnum")
}

fun fifth(){
  // declare a variable called num (int) and assign it whatever you want
  // declare a variable dnum (double) and assign it as follows:
  // if num > 100, assign dnum -234.567
  // if num < 100, assign dnum 444.555
  // if num == 100, assign dnum 0.0
  val num = Random((Math.random()*100).toInt()).nextInt()
  println("num is: $num")

  val dnum = when(num) {
    in Int.MIN_VALUE..99 -> 4444.5555
    100 -> 0.0
    in 101..Int.MAX_VALUE -> -234.567
    else -> 0.0
  }

  println("dnum is $dnum")
}

fun fourth() {
  for (i in 1..5){
    println(i)
    if (i == 2){
      break
    }
    jloop@ for (j in 11..20){
      println(j)
      for (k in 100 downTo 90) {
        println(k)
        if(k == 98) {
          break@jloop
        }
      }
    }
  }
}

fun third() {
  // first 15 numbers of fib series
  var first = 0
  var second = 1

  println ("$first")
  println ("$second")

  for(i in 1..13){
    var new = first + second;
    first = second;
    second = new;
    println("$second")
  }
}

fun second() {
  for(i in -500..0){
    print("$i ")
  }
}


fun first() {
  val range = 5..5000 step 5
  for (i in range){
    println("$i")
  }
}