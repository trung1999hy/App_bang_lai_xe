package com.example.drivingtest.model

import java.io.Serializable

class ExaminationModel(listQuestion: List<QuestionModel>) : Serializable {
    var listQuestion: List<QuestionModel> = listQuestion
}