# Kotlin

Learnings from _Kotlin for Java Developers_ [Udemy Training](https://www.udemy.com/course/kotlin-for-java-developers/)

# Basic Differences of Java and Kotlin

## Variable declarations

```kotlin
// val defines final / unmutable variables
val unmutableVariable = "abc"
val anotherUnmutableVariable : String
anotherUnmutableVariable = "assign value"

var mutableVariable = "Hello"
mutableVariable += " World"
mutableVariable = "Hello Computer"
```

> Prefered to use **val** instead of **var**

## Type aliases

- useful for generic types
- StringBuilder is a **typealias**

```kotlin
@SinceKotlin("1.1") public actual typealias StringBuilder = java.lang.StringBuilder
```

```kotlin
package com.wide.kotlin.test

typealias EmployeeSet = Set<Employee>

fun main() {
    val employees: EmployeeSet
}
```

## Differences between Java and Kotlin

- no **;** at the end of the line needed - but allowed
- kotlin has wrappers for lots of java standard libs (e.g. _print_ for _System.out.print_)
- keywords:
  - hard keywords only in java: keywords can only be for their intended usage
  - soft keywords only in kotlin: more relaxed usage - but shouldn't be used.
- accessing elements in collection via _[index]_ bossible
  ```kotlin
  val names = arrayListOf("John", "Jane", "Mary")
  println(names[1]) // prints 'Jane'
  ```
- Kotlin has its own [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/) class:
  - hides some method from the java String class
  - length in kotlin is a property (instead of an method)
- Exception
  Java: you need to declare on the message which Exception may be thrown
  Kotlin:
  - does not distinguish between checked and unchecked exceptions
  - **all Exceptions are unchecked**
  - no throws on methods needed
- ternary operator does not exist in Kotlin
- loops:
  - `for (i = 0, i < 5, i++)` does not exist
- Kotlin does not have the **_static_** keyword (from syntax view, principle exists)
- no **_new_** keyword:
  `val empyloyee = Emplyoee(20, "name", 10)`

## Equality (Referential vs structural equality)

- Java

  - checking for referential equality `a == b`
  - checking for structural equality `a.equals(b)`

- Kotlin
  - checking for referential equality `a == b`
  - checking for structural equality `a.equals(b)`

```kotlin
class Employee(var name: String, val id: Int){

    override fun equals(object: Any?): Boolean {
        if (objs is Employee){ // instance of

            // checks for structural equality
            return name == obj.name && id == obj.id
        }
        return false
    }
}

val e1 = Employee("Mary", 1);
val e2 = Employee("John", 2);
val e3 = Employee("John", 2);

println(e1 == e2)       // false
println(e2 == e3)       //  true (in java is also false)
println(e1.equals(e2))  // false
println(e2.equals(e3))  // true
```

> - in kotlin `==`and `a.equals(b)` _BOTH_ checks for structural equality (== links to equals())
> - Referential equality in Kotlin is checked via `===` operator

hence use the following code:

```kotlin
println(e1 === e2)      // false
println(e2 === e3)      //  true (in java is also false)
println(e1 == e2)       // false
println(e2 == e3)       // true
```

| Equality    | Java         | Java           | Kotlin                   | Kotlin  |
| ----------- | ------------ | -------------- | ------------------------ | ------- |
| Referential | **==**       | **!=**         | **===**                  | **!==** |
| Structural  | **equals()** | **! equals()** | **=** or **_.equals()_** | **!=**  |

## Bit Operators and Smart Casting

- use OR instead of |
- use AND instead of &
- use XOR

* Explicit cast

  ```kotlin
  val something: Any = employeeFour
  if (something is Employsee) { // instance of check
      val newEmployee = something as Employee     // Casting by using as
      println(newEmployee.name)
  }
  ```

- Implicit smart cast

  ```kotlin
  val something: Any = employeeFour
  if (something is Employsee) { // instance of check
      // after the is check a smart casting is emlicitly done by kotlin
      println(something.name)
  }
  ```

or negation while intsance of checking

    ```kotlin
    if (something !is Employsee) { // negative instance of check

    }
    ```

## String Templates

```kotlin
class Employee(var name: String, val id : Int) {

    override fun toString(): String {
        return "Employee(name=$name, id=$id)" // ref to property via $ keyword
    }
}

main(){
    print(Employee("Lynn", 10))
    // prints the String:
    // Employee(name=Lynn, id=500)

}
```

- StringTemplate: **"Employee(name=$name, id=$id)"**
- whenever using a variable within a string prefix the variable with **$**

## Raw Strings

- Or also called triple quoted strings
- inside no character needs to be esacaped

```kotlin
val filePath = """C:\somedir\subdir"""

val eggName = "Humpty"
val nurseryRhyme = """$eggName Dumpty sat on the wall
                    |$eggName Dumpty had a great fall
                    |All the king's horses and all the king's men
                    |Couldn't put $eggName together again.""".trimMargin()
println(nurseryRhyme)
```

- **|** is the default trim-character for **" ".trimMargin()**

## REPL (_Kotlin Read-Eval-In-Print-Loop_)

- More or less an interactive bash for kotlin
- Intellij: Tools -> Kotlin -> Kotlin REPL
- Runs in the Context of the module (mentioned in the tab name)
- previously declared variables can be reused

# Data Types and Null Reference Handling

## Builtin Datatypes in Kotlin

- everything is a class in Kotlin
- no lowercase datatype (like e.g. int in Java)

### Numbers

> In Kotlin the types are not autmatically widend/extended - the toXY() methods needs to be invoked
>
> ````kotlin
> val intVal = 10
> val longVal = intVal.toLong() ```
> ````

same is valid for floating point data types

### Char

```kotlin
val myChar = 'a'
val error: Char = 56    // no autoboxing

val myCharInt = 65
println(myCharInt.toChar())
```

### Boolean

nothing specific for boolean compared to Java

```kotlin
val trueValue = true
val falseValue = false
```

### Special classes

- **Any**: comparable with Object of Java (declaring equals, hashCode and toString methods)
- **Unit**:
  - used when Nothing is returned (in Java void is used)
  - Unit class exists in Kotlin
  - Is implemented as a singleton
  - hence in Kotlin every function returns a value (maybe the unit singleton instance)
- **Nothing**:
  - is a subclass of every class

## Arrays in Kotlin

- Arrays is a class as each other class
- Initialization

  ```kotlin
  // type is directly known
  val myArray = arrayOf("Thomas", "David", "Benni", "Dominik")

  val longArray = arrayOf<Long>(1, 2, 3, 4)
  val longArray2 = arrayOf(1L, 2L, 3L, 4L)

  println(myArray[2])       // Benni

  // Use Array constructor with lamba - i is the array index
  val evenNumbers = Array(16) {i -> i * 2}


  // declare Array with not yet known type
  val someArray: Array<Int>
  someArray = arrayOf(1, 2, 3, 4)


  // Any array implicit
  val mixedArray = arrayOf("hello", 22, BigDecimal(10), 'x)
  for (element in mixexArray){
    println(element)
  }

  ```

There are specific primitive type arrays (e.g. `val array = IntArray(5)` or `val array = intArrayOf(5, 6, 7, 8, 9)` ) - use them only if you plan to invoke Java code and need to pass arrays

- Another option is to convert the _kotlin_ array to an _java_ array by using `someArray.toIntArray()`
- For the other way round (java to kotlin conversion) there exists the method `toTypedArray()` to convert the java array

## Null References in Kotlin (1)

- defining a variable that may be null needs to have an ? after type decalaration

  `val str: String? = null`

- Safe Call Operator

  - Syntax is _?._
  - accessing an attribute of an potential nullable value using the **_save operator ?_**:

  `println("What happens when we do this: ${str?.uppercase()}")`
  Will return

  > What happens when we do this: null

- **_Elvis Operator_**:

  - Syntax is _?:_
  - returns a default value in case the expression on the left of the operator returns null

- **_Safe cast Operator_**:

  - Syntax: _as?_
  - checks safely if it can be casted - else it is casted to 'null'

  ```kotlin
    val something: Any = arrayOf(1,2,3,4)
    val str = something as? String
    println(str)                            // null
    println(str?.uppercase())               // safe-operator ? required!
  ```

## Null References in Kotlin (2)

- **_Not-Null Assertion Operator_**

  - Syntax: _!!_
  - applies in cases where we're sure that the variable cannot be null (but the compiler cannot infer correctly)
  - apply !! instead of ?

    ```kotlin
        val str: String? = "This isn't null"

        val str2 = str.toUpperCase()            // not working as str may be null based on the declaration

        val str3 = str!!.toUpperCase()          // as value is assigned above we're sure that str is not null
    ```

  - This forces Kotlin to throw an NPE - this should be explicitly wanted (in case connection to database cannot be established and hence to application cannot work as expected)

  - NPE thrown from above is not the Java NPE, it is a **_KotlinNullPointerException_**

  - Don't do the following, as KNPE doesn't provide which Reference is null
    ```kotlin
    val countryCode = bankBranch!!.address!!.country!!.countryCode
    ```

- **_Let-Function_**

  - e.g. the defined function expects a non-null parameter, but the parameter you want to pass is defined as option

    ```kotlin
    fun printText(text:string){         // text may not be null!!
        print(text)
    }

    val text: String? = "some value"
    printText(text)                     // will not work

    if (text != null){
        printText(text)                 // works, but not handy
    }
    ```

  - using the **let-function**:

    ```kotlin
    val text: String? = "some value"
    str?.let { printText(it) }  // let() function only invoked if str != null
                                // let() consumes lambda - _it_ is default name
    ```

## Arrays and Null References

- Creating an 'empty' array / nullable array

  ` val nullableInts = arrayOfNulls>Int?>(5)`

  initializes an Int array of size 5 containing value null at the moment.

  also possible (due to arrayOfNulls):
  ` val nullableInts = arrayOfNulls>Int>(5)`

# OO and Kotlin: Classes, Functions, Inheritance

## Kotlin's Access Modifiers

## Declaring Classes and Using COnstructors in Kotlin

## Properties and Backing Fields in Kotlin

## Constants and Data Classes

## Kotlin Function Basics

## Extension Functions

## Inline Function

```kotlin

```
