package com.example.test2

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            timer(20000)
        }
    }

    println("Time: $time")


    //println("egor".double())
}



private suspend fun doWorld() {
    coroutineScope {
        repeat(10) {
            launch {
                delay(1000)
                println("World!")
            }
        }
        launch {
            delay(2000)
            println("World! 2")
        }
        println("Hello, ")}


}
//fun String.double() = this + this

private suspend fun makeComputation(): Int = coroutineScope {
    val res1 : Deferred<Int> = async { computation1() }
    val res2 : Deferred<Int> = async { computation2() }

    res1.await() + res2.await()
}

suspend fun computation1() : Int {
    delay(2000L)
    return 2
}

suspend fun computation2() : Int {
    delay(3000L)
    return 3
}


private suspend fun timer(time: Long) = coroutineScope {
    var total = 0L
    while (total < time) {
        val deltaDelay : Long = when {
            total < 2000 -> 100
            total < 10000 -> 1000
            else -> 2000
        }
        println("$total")
        delay(deltaDelay)
        total += deltaDelay
    }

}
