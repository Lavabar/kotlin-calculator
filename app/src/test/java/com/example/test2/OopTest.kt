package com.example.test2

abstract class Animal1 () {
    internal abstract var age: Int
    internal abstract var weight: Int
    fun printAgeWeight() {
        println("$age $weight");
    }
}

class Cat1(override var age: Int,
           override var weight: Int):Animal1() {
}


fun main() {
    val a = Cat(age = 1, weight = 4)
    a.printAgeWeight()
    a.makeVoice()
}

abstract class Animal (var age: Int, var weight: Int) {

    fun printAgeWeight() {
        println("$age $weight")
    }
}

class Cat(age: Int,
          weight: Int):Animal(age, weight), Voice {
    override val voice = "meow"

}

interface Voice {
    val voice : String
    fun makeVoice() {
        println(voice)
    }
}