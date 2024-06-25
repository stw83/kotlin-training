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

- Kotlin can have attributes & functions on top and on class level
- Visibilities in Kotlin
  - public
  - private
  - proteted
  - **internal**

### Top-Level Items
- Top level item are public by default (in java they are package private)
- If top level item is delcared as private, only all code inside the same class can access it (Java can only have one public class per source file)
  - Kotlin supports definition of top level private classes (java not as nobody may see them)

the following code is valid in kotlin:
```kotlin

fun main(){
  val emp = Employee();
  print(emp);
}

private class Employe{}
```
- protected is not available for top-level items
- top-level items defined with **internal** are available within the same module (group of files)

!(img/topLevelItemsAccesModifiers.png "Overview for TopLevel Items")

### Class internal items
more or less the same for java and kotlin

### using kotlin and java in parallel
there are situations when java can see kotlin properties that you cannot see in kotlin. COmpiler makes attribute named ugluy so that you should get it while using by the strange name.

## Declaring Classes and Using Constructors in Kotlin

- in kotlin all classes are ***public final*** by default

### long version of class with constructor
```kotlin
// primary constructor is defined outside of the class
class Employee constructor(firstName: String){

  val firstName: String

  // Initialzer block - is NOT a constructor!!
  init {
    thist.firstName = firstName
  }

  // further secondary constructors can be defined here
}
```
### shorter version
this is possible if in the constructor/init only needs simple variable/property assignments
```kotlin
class Employee constructor(firstName: String){
  val firstName: String = firstName;
}
```

### more shorter definition
```kotlin
class Employee constructor(val firstName: String){}
```

by adding val to the primary constructor the compiler automatically derives that this is a class property and assigns the passed value to the class property having the same name as defined in the primary constructor

### shortest definition

as it is known that outside of the class block this is the definition of the primary constructor there is no need to write 'constructor*

```kotlin
class Employee(val firstName: String){}
```

in case annotations for an argument or access modifiers for the constructors are required this short version does not work, e.g. in case of this:

```kotlin
class Employee proteced constructor(@NonNull val firstName: String){}
```

### Having default values in constructors
often multiple constructors are provided given the option to define some attributes, if they are not set some default values will be used by the constructors and pass it to the constructors with all args

#### long version
```kotlin
class Employee(val firstName: String){

  val fullTime: Boolean = true

  // No 'val firstName' as this member attribute is 'controlled' by primary constructor
  // ':this(firstName)' describes invocation of primary constructor pasing firstName prop
  constructor(firstName: String, fullTime: Boolean ): this(firstName) {
    // primary constructor needs to delegete/call primary constructors --> done by ':this()'
    this.fullTime = fullTime;
  }
}
```
- val/var in constructor definition of secondary constructors are not creating this property and hence they are not allowed (only passing the arg is possible - not creating the member/property as well - this can only be done in the primary constructor)


#### shorter version
```kotlin
class Employee(val firstName: String, var fullTime: Boolean = true){}
```
- fullTime is defined as an optional parameter within the primary constructor

#### Reducing number of constructors
Idea is to provide all the parameters by the primary constructor and having default values - hence it should be possible to reduce the number of constructors dramatically.

```kotlin
class Employee(val firstName: String, var fullTime: Boolean = true)
```

### Without primary constructor
* possible to have class without a primary constructor - this means now `()` after the name of the class
* Without a primary constructor it is possible to have multiple secondary constructors
* It's also possible to set default values in secondary constructors

```kotlin
class Demo{
  val dummy String;

  constructor() {
    dummy = "Hello"
  }
}
```

## Properties and Backing Fields in Kotlin
* Kotlin only has properties - no fields --> see 
[Docu](https://kotlinlang.org/docs/properties.html#properties-and-fields)
* by default props have visibility ***public**
* Accessing public props by <objectName>.<propertyName> - assinging a value can be done by `emp.fullTime = true` -> this call is redirectired to the setter method
* if a property is defined as private in Kotlin, it is not possible to change / access the value from outside - even if you generate getter/setter for the property
* getters/setters in kotlin must have the same visibility as the property has
* In case the default generated getters and setters (having same visibility as the prob) are not sufficient, it is possible to write own getters and setters
  * in this case, they need to be written/created explicitly inside the class (hence not within the primary constructor)
  * The argument in the parameter of the primary Constructor is just there, but it is not a declaration - Kotlin won't create an assign the value automatically
  * parameter declaration needs to be done by our one
  * in our onw getter/setter methods we need to invoke the Backing Field `field`
  * Setting values within the constructors, the custom setter method ***is not*** invoked

```kotlin
class Employee(val firstName: String, fullTime: Boolen = true){
  // fullTime is only a parameter, not a declaration anymore!

    // Getter/Setter is generated automatically
    var fullTime = fullTime
    // Custom geters/setters immedeately after the property declaration
    get(){
      print("Running the custom get")
      // Backing field - only place were we can access it directly
      return field
    }
    set(value){
      print("Running the custom set")
      field = value
    }

  // will access our own getter method
  print(Employee().fullTime)

}
```

## Constants and Data Classes

* Kotlin supports TopLevel functions and constants - so no/less need for static classes
* Constants typically created as VAL

```kotlin

val MY_CONST=100

fun main(){
  print(MY_CONST)
}
```

### Data Classes
+ typically used for storing data/state - only having fields with according getters and setters without any further logic
* are defined by `data class <NAME>`

```kotlin
data class Car(var color: String, val model: String, val year: Int) {

}

fun main(){
    val car1 = Car("blue", "Daimler", 2015)
    val car2 = car1.copy()
    // copy and change property
    val car3 = car1.copy(year = 2016, color = "green")

    println(car1 == car2)     // true - auto-generated 
}

```
* data classes have a toString(), hashCode(), equalTo() and copy() method implemented by default - and can be overriden if required

* Primary Constructor of data classes needs to have at least one parameter
* All parameter of primary constructor needs to be marked with `val` or `var`
* data classes cannot be abstract or sealed (see inheritance) or inner class

## Kotlin Function Basics

* syntax of functions is identical independent if they are declared inside or outside of a class
* basic sytax is `fun <funName> (<parameterList>) <:returnType>{}`
  * fun is the keyword
  * name fo the function
  * parameterList including optional default value (even if a default value is provided the parameter type needs to be defined and **cannot be derived by the compiler**!)
  * return type - default return type is `unit` and does not need to be specified

```kotlin
fun labelMultiply(operand1: Int, operand2: Int, label: String): String {
  return ("$label ${operand1 * operand2}")
}
```
* This function has an *** block - body *** - inside the curley braces

### Simplification of labelMultiply function

* as we only have one line no return is required, hence also no brackets

```kotlin
fun labelMultiply(operand1: Int, operand2: Int, label: String =" The answer is:") =
  "$label ${operand1 * operand2}"
```
* Function returns everything after the `=` sign
* return type is inferred automatically
* This function has an *** expression - body *** - after the equal sign 
* Invoke method with random parameter order - using **named arguments** (can also be used if the order doesn't differ ):

  `println(labelMultiply(label = "Here's the result: ", operand2 = 3, operand1 = 4))`

### Vararg parameter
* providing multiple(undefined) values of same type, dann use parameter type vararg
* only one vararg is allowed as parameter
* vararg does not need to be the last parameter in den function declaration, but then named arguments need to be used!

### Spread-Operator
  ```kotlin
  fun main(){
    // arrayOf uses vararg as parameter
    val manyCars = arrayOf(car1, car2, car3)

    printColors(manyCars)       // not working as type missmatch (this is working in Java)

    // using spread operator *
    printColors(*manyCars)      // elements of array are passed --> allowed/working
    
  }

  fun printColors(vararg cars: Car) {
    for (car in cars) {
      println(car.color)
    }
  }
  ```

* Spread-Operator can be used anytime we **want to unpack the elements of an array**
  ```kotlin
  val moreCars = arrayOf(car2, car3)
  val car4 = car2.copy()

  val lotsOfCarsWrong = arrayOf(manyCars, moreCars, car4)   // here arrays inside an array
  val lotsOfCars = arrayOf(*manyCars, *moreCars, car4)      // array of car elements
  ```

## Extension Functions

* Adding function to existing classes - without creating subclasses
* is only a syntaxical illusion

```kotlin
// Java way
class Utils {
  fun upperFirstAndLast(str: String): String {
    val upperFirst = str.substring(0,1).toUpperCase() + str.substring(1)
    return upperFirst.substring(0, upperFirst.length - 1) + 
            upperFirst.substring(upperFirst.length - 1, upperFirst.length).toUpperCase()
  }
}
```
* Would be desirable to add this function to the java/kotlin string class

```kotlin
// Extension function way

// Prefix the method name with the class the function should be associated to (String class is the receiver type)
fun String.upperFirstAndLast(): String {
  val upperFirst = this.substring(0, 1).toUpperCase() + this.substring(1)
  return upperFirst.substring(0, upperFirst.length - 1) + 
           upperFirst.substring(upperFirst.length - 1, upperFirst.length).toUpperCase()
}

// invocation of extension function
val s = "all in lower case"
s.upperFirstAndLast()
```

### Simplification

* `this` inside the extension function is not required and can be removed!

```kotlin
// Extension function way

// Prefix the method name with the class the function should be associated to (String class is the receiver type)
fun String.upperFirstAndLast(): String {
  val upperFirst = substring(0, 1).toUpperCase() + substring(1)
  return upperFirst.substring(0, upperFirst.length - 1) + 
           upperFirst.substring(upperFirst.length - 1, upperFirst.length).toUpperCase()
}
```

## Inline Function

* works best for functions with lambda parameters
* often compiler inlines on his own
* is done by using keyword inline

  `inline fun labelMultiply(operand1: int, operand2: int, label: String) ...`

* after compilation the inline function is inlined and no function call is made during runtime

## Inheritance in Kotlin - Part 1

* everything in Kotlin is `public final` (classes, toplevel functions, ...)
* 

### With primary constructor
we have an empty primary constructor doing nothing
```kotlin
// needs to be defined with open to enable beeing extended (without class is final!)
// () is the primary constructor
open class Printer() {

}

// LaserPrinter extends Printer (: is the syntax for extending)
// by both () constructors are added and invoked accordingly
class LaserPrinter(): Printer() {
}
```

###  Without primary constructor
```kotlin
// needs to be defined with open to enable beeing extended (without class is final!)
// () is the primary constructor
open class Printer {

}

// LaserPrinter extends Printer (: is the syntax for extending)
class LaserPrinter: Printer {
  constructor(): super()
}
```
### Properties and using abstract class
* in case of abstract classes, open keyword is not required as it is redundant as abstract class needs to be extended as not class can be instantiated
```kotlin
abstract class Printer(val modelName: String) {
  // make function overridable
  open fun printModel() = println("The model name of this printer is $modelName")
  // abstract functions are open by default (as they need to be overridden)
  abstract fun bestSellingPrice(): Double
}

// as modelName is defined in base class no val/var in the arg list!
class LaserPrinter(modelName: String): Printer(modelName) {
  // override keyword needed
  override fun printModel() = println("The model name of this laser printer is $modelName")

  override fun bestSellingPrice(): Double = 129.99
}
```

## Inheritance in Kotlin - Part 2

* by adding override it means that subclasses may also override this function
* if subclasses should not override a function, add `final` keyword
  ```kotlin
  abstract class Printer(val modelName: String) {
    open fun printModel() = println("The model name of this printer is $modelName")
    abstract fun bestSellingPrice(): Double
  }

  open class LaserPrinter(modelName: String): Printer(modelName) {
    // avoid overriding in sublcass using explicit final keyword
    final override fun printModel() = println("The model name of this laser printer is $modelName")
    override fun bestSellingPrice(): Double = 129.99
  }

  class ColorLaserPrinter(modelName: String): LaserPrinter(modelName) {
    // compile error as parent method is defined final
    override fun printModel() = println("this is my way of doing in")
  }
  ```

### Parameters of primary constructors of subclasses don't need to match
  ```kotlin
  abstract class Printer(val modelName: String) {
    open fun printModel() = println("The model name of this printer is $modelName")
    abstract fun bestSellingPrice(): Double
  }

  open class LaserPrinter(modelName: String, val ppm: Int): Printer(modelName) {
    // avoid overriding in sublcass using explicit final keyword
    final override fun printModel() = println("The model name of this laser printer is $modelName")
    override fun bestSellingPrice(): Double = 129.99
  }

  class ColorLaserPrinter(modelName: String): LaserPrinter(modelName) {
    // compile error as parent method is defined final
    override fun printModel() = println("this is my way of doing in")
  }
  ```

Remember:
* Secondary constructors need to delegate to primary constructor
* invocation of only secondary constructor only possible if no primary constructor exists (???)

  ```kotlin
  // class without primary constructor
  open class Something {
    val someProperty: String

    // secondary constructor
    constructor(someParameter: String){
      someProperty = someParameter
    }
  }

  class SoemthingElse: Something {
    constructor(someOtherParameter: String) : super(someOtherParameter)
  }
  ```

  * Adding primary constructors to above example:
    * There are several problems / challenges using primary and secondary constructors in common and defining responsiblity of who is initalizing which property (especially if they are val)

  ```kotlin
    // class without primary constructor
    open class Something(val x: Int) {
      val someProperty: String

      // secondary constructor
      constructor(someParameter: String, y: Int): this(y) {
        // not sufficient to only initialize it in secondary constructor
        someProperty = someParameter
      }
    }

    class SoemthingElse: Something {
      constructor(someOtherParameter: String, z: Int ) : super(z)
    }
  ```
  using only secondary constructors:
  ```kotlin
    open class Something {
      val someProperty: String

      constructor(someParameter: String) {
        someProperty = someParameter
        println("inside parents constructor")
      }
    }

    class SoemthingElse: Something {
      constructor(someOtherParameter: String):super(someOtherParameter) {
        println("inside childs constructor")
      }
    }
    ```

* Data classes are closed type and cannot be extended!

## Interface in Kotlin
* not much differences compared to java interfaces
* syntax differs
* Interfaces are extendable by default (no open required)

```kotlin
interface MyInterface {

  // interfaces may have properties (property is currently abstract)
  val number: Int
  // val number2: Int = 50 // error as property initializers are not allowed
  val number2: Int
  get() = number * 100;    // default value by doing getter magic and override it

  fun myFunction(str: String): String
}

interface MySubInterface:MyInterface {
  
  fun mySubFunction(num: Int): String
}

open class Something: MySubInterface {

  override val number: Int = 25

  override fun myFunction(str: String): String {

  }

  override fun mySubFunction(num: Int): String {
    
  }
}
```
* Properties in interfaces don't have `backing fields`, hence `field` accessor doesn't work

## Singletons in Kotlin
* Kotlin can declare a class and final instance at once using the `object` keyword
* This is typically used for the following three use cases:
  * Singleton
  * Companion Objects
  * Object Expressions

### Singleton
To create an singleton object use the object keyword and it is ensured that only one instance of this class will exist

```kotlin

fun main(){
  println(CompanyCommuications.getTagLine()))
  println(CompanyCommuications.getCopyrightLine()))
}

// only have one Company Communication for the whole company --> singleton
object CompanyCommuications {
  val currentYear = Year.now().value

  fun getTagLine() = "Our company rocks!"
  fun getCopyrightLine() = "Copyright \u00A9 $currentYear Our Company. All rights reserved."
}
```
* accessing the methods of the object by using the class name
* Singleton object is created the first time the class is used
* Object declarations can extend classes or interfaces

## Companion Objects in Kotlin

* Second use case for the `object` keyword
* As static keyword doesn't exist in kotlin , instead we use topLevel properties and functions
*  

```kotlin
class SomeClass {
  private val privateVar = 6

  fun accessPrivateVar() {
    println("I'm accessing privateVar: $privateVar")
  }
}
```

* It is not possible to access/invoke accessPrivateVar without having an instance of this class (as we would have in java with a static method)
* To make this possible in Kotlin, we have to use Companion Objects

```kotlin
class SomeClass {
  companion object {
    private var privateVar = 6
    
    // Accessor also needs to be inside companion object!
    fun accessPrivateVar() = "I'm accessing privateVar: $privateVar"
  }
}

fun main(){
  // Accessing method of companion object
  println(SomeClass.Companion.accessPrivateVar())
}
```

* More or less anything inside the companion object is more or less static (like it would be in Java)

As kotlin is smart enough to know that we want to use the companion object the following code is also valid:
fun main(){
  // Accessing method of companion object - old way
  //println(SomeClass.Companion.accessPrivateVar())
 
  println(SomeClass.accessPrivateVar())
}

### Named Companion object

We can name the companion Object - then the accessor needs also to use this named companion

```kotlin
class SomeClass {
  companion object SomeCompanion {
    private var privateVar = 6
    
    // Accessor also needs to be inside companion object!
    fun accessPrivateVar() = "I'm accessing privateVar: $privateVar"
  }
}

fun main(){
  // Accessing method of companion object
  println(SomeClass.SomeCompanion.accessPrivateVar())
}
```

### Further use case for company objects
* Invoking private constructor - hence use it for Factory-Pattern

* Approach without company object
```kotlin
class SomeClass {
  val someString: String

  constructor(str: String) {
    someString = str
  }

  constructor(str: String, lowerCase: Boolean) {
    if (lowerCase){
      someString = str.toLowerCase()
    } else {
      someString = str.toUpperCase()
    }
  }
}

fun main(){
  // Accessing method of companion object
  println(SomeClass.SomeCompanion.accessPrivateVar())
}
```

* Approach using company object - Factory Pattern
  * as constructor shouldn't be invoked from outside directly the primary constructor neeeds to be defined as private (--> private constructor)
```kotlin
class SomeClass private constructor(val someString: String) {

  copanion object {
    fun justAssign(str: String) = SomeClass(str) 
    fun upperOrLowerCase(str: String, lowerCase: Boolean): SomeClass {
      if (lowerCase){
        return SomeClass(str.toLowerCase())
      } else {
        return SomeClass(str.toUpperCase())
      }
    }
  }

  fun main(){
    val someClass1 = SomeClass.justAssign("this is the string as it is")
    val someClass2 = SomeClass.upperOrLowerCase("this isn't the string as it is", false)

    // INVALID as constructor is private!
    val someClass3 = SomeClass()
  }
}
```

## Anonymous Objects in Kotlin


```kotlin
interface SomeInterface {
  fun mustImplement(num: Int) : String
}

fun wantsSomeInterface(si: SomeInterface) {
  println("Printing from wantsSomeInterface ${si.mustImplement(22)})
}
```

Invoking with inner class / anonymous instances:
```kotlin
fun main(){
  wantsSomeInterface(object: SomeInterface {
    override fun mustImplement(num: Int) = "This is from musterImplement: ${num * 100}"
  })
}
```

## Enums in Kotlin

* Very similar to Enums in Java

```kotlin
enum class Department {
  HR, IT, ACCOUNTING, SALES
}
```

With properties and functions:
```kotlin
enum class Department(val fullName, val numEmployees: Int) {
  HR("Human Resources", 5), 
  IT("Information Technology", 10),
  ACCOUNTING("Accounting", 3),
  SALES("Sales", 20)
}
```

Adding functions:
```kotlin
enum class Department(val fullName, val numEmployees: Int) {
  HR("Human Resources", 5), 
  IT("Information Technology", 10),
  ACCOUNTING("Accounting", 3),
  SALES("Sales", 20);           // <-- semicolon is required here 

  fun getDeptInfo() = "The $fullName department has $numEmployees employees"
}
```

```kotlin
fun main(){
  println(Department.ACCOUNTING.getDeptInfio())
}

```

## Imports in Kotlin

* packages not required in Kotlin
* classnames may not match the filename they are declared in
* Imports can be named customly using the `as` keywor

  `import com.abc.String as MyString`
* keyword `as` is also applicable for top level functions

## The Internal Access Modifier

* private means to be **visible within the same file**
* internal means visible within the same module!

## Kotlin Challenge (3.1)

## Kotlin Challenge (3.2)


# Loops, and the if, When, and Try/Catch Expressions

## For Loop
* typical for loop `for (int i = 0; i < 10; i++)` doesn't exist in Kotlin
* Kotlin uses **range-loop** following `for (i in 1..5)`
  * typically is used with range: `var range = 1..5` (1 and 5 are ***included in the range***!!)
  * char range: `var charRange = 'a'..'z'` (a and z are ***included in the range***!!)
  * string range: `var stringRange = 'ABC'..'XYZ'` 
  * used types needs to be comparable as they are compared with the in Operator of the loop
  * backwards-ranges: `var backwardRange = 5.downTo(1)` - this is not the same as `5..1`
  * `1..5.reversed()` - only for numeric and charater types
  * `for ( x in 'ABC'..'XYZ')` is not working as for the string range no iterator exists - just the comparable functionality is available to test if a provided value is in the defined range
  * `for (c in "Hello")` is working as string class has an iterator that is used
  * `var myRange = 1..20` and `myRange.step(4)` defines the step width and results in 1, 5, 9, 13, 17
  * same without a variable `for (num in 1..20 step 4)` 
  * `for ( i in 20 downTo 15)` results in 20, 19, 18, 17, 16, 15
* Using keyword **until** to not include the end value (exclusive):
  `for (i in 1 until 5)` results in 1, 2, 3, 4
* iterating over arrays
  * `for (season in arrayOf("spring", "summer", "fall", "winter")) {print(season)}`
  * `val notAseason = "blub" !in arrayOf("spring", "summer")`
  * using index:
    ```kotlin
    val seasons = arrayOf("spring", "summer", "fall", "winter")
    for (index in seasons.indices) {
      println("${seasons[index]} is season number $index")
    }
    ```
  * using lambda: ``` seasons.forEach { println(it) }```
  * using lambda with index: 
    ` seasons.forEachIndexed { index, value -> println("$value is season number $index") }`
  
### Named Loop
* especially if using nested loops and want to break / stop different loops
* in die example in most innerst loop (k) want to break the parent look (j - jloop)
  ```kotlin
    for (i in 1..5){
      println("i = $i")
      jloop@ for (j in 1..5){
        println("j = $j")
        for (k in 10..14) {
          println("k = $k")
          if (k == 12) {
            break@jloop;
          }
        }
      }
    }
  ```

## If Expression
* If-condition can evaluate to an value and not only boolean
* ternary operator does not exists in kotlin - but short is: `val num = if (someCondition) 50 else 60`
* if expression allow to return value of the if and else block to assign to a variable:
  ```kotlin
  val num2 = if (someCondition){
    println("something)
    50
  } else {
    println(" something else)
    60
  }

  println(num2)   // 50 or 60
  ```
  in this case, the else branch must exist in any way as num2 needs to be assigned

## When Expression
* > switch - Statement on sterioids
* 
  ```kotlin
  val num = 200
  val offset = 500
  when (num) {
    100, 600 -> println("100")    // multiple condition run same code
    200 -> println("200")
    in 300..399  -> println("300")    // validating against ranges
    offset + 100 -> print("offset")   // dynamic condition

    else -> println("Doesn't match to anything relevant")
  }
  ```
  * no break statement required - is added by compiler automatically.
  * Hence only exactly one branch is executed and no "fall-through" is possible by `when`
  
* matching class type within when expression
  ```kotlin
  val something: Any = ....
  val z = when (something) {
    is String -> println(something.toUpperCase())
    is BigDecimal -> println(something.remainder(BigDecimal(10.5)))
    is Int -> println("${something - 22}")
    // Without an default branch the compiler is not happy as a value needs to be returned and assigned to variable z
    else -> println("no idea which type)    // return kotlin.Unit
  }
  ```

## Try/Catch Expression
* idea same as in java
* can be used as expression in Kotlin
* Kotlin doesn't distinguish between checked and unchecked Exceptions
* ```kotlin
  fun getNumber(s: String): Int {
    return try {
      Integer.parseInt(str)       // returned as it is last value in the block
    }
    catch(e: NumberFormatException) {
      0                           // returned as it is last value in the block
    } finally {
      // no value can be returned from finally block
      println("This is the finally block...")
      -10                         // will NOT be returned!
    }
  }
  ```
  
*
  ```kotlin
  fun getNumber(s: String): Int? {
    return try {
      Integer.parseInt(str)
    }
    catch(e: NumberFormatException) {
      null
    } finally {
      println("This is the finally block...")
    }
  }

  fun main(){
    // using Elvis-Operator to throw an exception
    println(getNumber("22.5") ?: throw IllegalArgumentException("Number isn't an Int))
  }
  ```

## Kotlin Challenges (4.1)

# Lambda Expression, Collections, and Generics

## 
## ...
## ...
## ...
## ...
## ...
## ...

# File I/O

## Reading text files

## Reading Binary Files and Try with Resources

## Walking the File Tree

# Java Interoperability

## Nullability when using Java from Kotlin

## More about calling Java from Kotlin

## Calling Kotlin Functions from Java

## Annotations when calling Kotlin from Java

## Kotlin Challenges (Round 6 - 6.1)

# Course Wrap Up

## Revisit the Converted Kotlin Text Adventure

## kotlin for Java Developers Wrap Up



```kotlin

```
