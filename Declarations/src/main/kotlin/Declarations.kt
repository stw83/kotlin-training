package org.example

fun main(){
  val immutableVariable = "fixValue"
  val immuatableInt : Int
  immuatableInt = 10

  val autoboxedString: Short = 25

  val list = listOf("a", "b")


  var mutableVariable: String
  mutableVariable = "now assigned a value"
  mutableVariable = "changed assignment"

  print(mutableVariable)

  val employee = Employee("John Doe", 400)
  employee.name = "John Smith"
  print(employee)

  val employee2: Employee


  }


class Employee(var name: String, val id: Int)
