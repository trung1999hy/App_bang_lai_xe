package com.example.drivingtest.model

import java.io.Serializable

class TakeExamModel(listQuestion: List<QuestionsModel>) : Serializable {
    var listQuestion: List<QuestionsModel> = listQuestion
}