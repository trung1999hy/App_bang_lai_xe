package com.example.drivingtest.model

import java.io.Serializable

data class QuestionsModel(
    var id: Int = 0,
    val questions: String? = null,
    val image: Int = 0,
    val A: String? = null,
    val B: String? = null,
    val C: String? = null,
    val D: String? = null,
    val result: String? = null,
) : Serializable {
    var lcA: Int = 0
    var lcB: Int = 0
    var lcC: Int = 0
    var lcD: Int = 0
}
