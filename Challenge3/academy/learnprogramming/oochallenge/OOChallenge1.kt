package academy.learnprogramming.oochallenge


fun main() {
  val kotlinBike = KotlinBicycle(1, 2, 3)
  val kotlinMountainBike = KotlinMountainBike(10, 1, 2, 3)
  val kotlinRoadBike = KotlinRoadBike(24, 1, 2, 3)

  println(kotlinBike)
  println(kotlinMountainBike)
  println(kotlinRoadBike)

  kotlinBike.applyBrake(1)
  println(kotlinBike)
  kotlinBike.speedUp(5)
  println(kotlinBike)

  val kotlinColoredMountainBike = KotlinMountainBike(10, 1, 2, 3, "red")
  println(kotlinColoredMountainBike)
}

open class KotlinBicycle(var cadence: Int, var gear: Int, var speed: Int) {

  fun applyBrake(decrement: Int) {
    speed -= decrement
  }

  fun speedUp(increment: Int) {
    speed += increment
  }

  override fun toString() = "KotlinBicycle(cadence: $cadence - gear: $gear - speed: $speed)"
}

class KotlinMountainBike(var seatHeight: Int, cadence: Int, gear: Int, speed: Int) :
  KotlinBicycle(cadence, gear, speed) {

  companion object BikeColor {
    private val COLORS_AVAILABLE = listOf("blue", "red", "white", "black", "green", "brown")
    fun availableColors(): List<String> = COLORS_AVAILABLE
  }

  constructor(seatHeight: Int, cadence: Int, gear: Int, speed: Int, color: String) : this(
    seatHeight,
    cadence,
    gear,
    speed
  ) {

    println("available colors are: ${BikeColor.availableColors()}")
    println("Color of mb created is $color")
  }


  override fun toString() =
    "KotlinMountainBike(seatHeight: $seatHeight - cadence: $cadence - gear: $gear - speed: $speed)"
}

class KotlinRoadBike(val tireWidth: Int, cadence: Int, gear: Int, speed: Int) :
  KotlinBicycle(cadence, gear, speed) {
  override fun toString() = "KotlinRoadBike(tireWidth: $tireWidth - cadence: $cadence - gear: $gear - speed: $speed)"
}

