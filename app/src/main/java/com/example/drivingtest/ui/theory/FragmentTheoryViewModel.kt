package com.example.drivingtest.ui.theory

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivingtest.local.questions.DatabaseQuestionAccess
import com.example.drivingtest.model.QuestionModel
import kotlinx.coroutines.launch

class FragmentTheoryViewModel : ViewModel() {
    private val _theory = MutableLiveData<ArrayList<QuestionModel>>()
    val theory : LiveData<ArrayList<QuestionModel>> = _theory

    fun getDataTheory(count : Int, context: Context) {
        viewModelScope.launch {
            val dataTheory = DatabaseQuestionAccess.getInstance(context).getQuestionsTheory(count)
            _theory.postValue(dataTheory as ArrayList<QuestionModel>?)
        }
    }
}