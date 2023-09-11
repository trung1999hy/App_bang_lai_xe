package com.example.drivingtest.model

data class TipsModel(
    var id: Int = 0,
    var kind: Int = 0,
    var content: String? = null,
    var lock: Boolean = false
)
