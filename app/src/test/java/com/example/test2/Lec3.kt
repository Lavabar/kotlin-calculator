package com.example.test2

fun main() {

    /*
    *
    * val animals = listOf(Cat(1, 2), Cat(2, 5))
    * for (animal in animals) {
    *     animal.makeVoice()
    * }
    * for (i in animals.indices) {
    *     println(animals[i].age)
    * }


    animals.forEach {

    }
     */



    val animals = listOf(Cat(1, 2), Cat(10, 10), Cat(15, 10))
    println(getSumAge((animals)))
    println(animals.sumOf { it.age })
    var cats = animals.filter { it is Cat }
    println(cats)
    var cats2 = animals.filterIsInstance<Cat>()
    println(cats2)
    val fatAnimals = animals.first { it.weight > 5 }

    while (true) {
        println(":)")
    }
}

fun getSumAge(animals: List<Animal>): Int {
    var res : Int = 0
    animals.forEach {
        res += it.age
    }
    return res
}

