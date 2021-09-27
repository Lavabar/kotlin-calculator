package com.example.test2

fun main(){
    val feedback = listOf(
        Feedback(1, null),
        Feedback(null, "Всё супер"),
        Feedback(5, null),
    )
    println(averageRating(feedback))
}

fun averageRating(feedbacks: List<Feedback>): Float {

    var res : Float = 0F
    var n : Int = 0
    feedbacks.forEach {
        if (it.rating != null) {
            res += it.rating
            n += 1
        }
    }

    return res / n
}

fun averageRating2(feedbacks: List<Feedback>): Float {

    var count = feedbacks.count { it.rating != null }
    var sum = (feedbacks.sumOf { it.rating ?: 0 }).toFloat()
    return sum / count
}

//fun averageRating3(feedbacks: List<Feedback>): Float = feedbacks.sumOf { it.rating?.toFloat() ?: 0f } / feedbacks.count()

class Feedback(
    val rating: Int?,
    val comment: String?
)