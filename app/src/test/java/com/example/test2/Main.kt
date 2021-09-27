package com.example.test2

fun main() {
    println("Hello, world!!!")
    val a = 34
    var b: String = "sdfsdf"

    test(1, 2)

    println(weightToGram(933))

}

fun test(a: Int, b: Byte): String {
    println("$a and $b")
    return when {
        a == 1 -> "Windows"
        a == 2 -> "Linux"
        else -> "MacOS"
    }
}

fun weightToGram(weightGram: Int): String {
    if (weightGram >= 1000) {
        var tmp = weightGram.toDouble() / 1000
        return "$tmp кг"
    } else {
        return "$weightGram г"
    }
}

