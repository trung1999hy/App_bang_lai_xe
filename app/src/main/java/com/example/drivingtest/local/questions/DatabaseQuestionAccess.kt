package com.example.drivingtest.local.questions

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.drivingtest.model.QuestionModel

class DatabaseQuestionAccess private constructor(context: Context) {
    private val openHelper: SQLiteOpenHelper = DatabaseQuestionHelper(context)
    private var database: SQLiteDatabase? = null

    companion object {
        private var instance: DatabaseQuestionAccess? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseQuestionAccess {
            if (instance == null) {
                instance = DatabaseQuestionAccess(context)
            }
            return instance as DatabaseQuestionAccess
        }
    }

    private fun openDatabase() {
        database = openHelper.readableDatabase
    }

    private fun closeDatabase() {
        database?.close()
    }

    fun getQuestions(): List<QuestionModel> {
        val listQuestions = ArrayList<QuestionModel>()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM CauHoi", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id: Int = cursor.getInt(0)
                val question: String = cursor.getString(1)
                val image: Int = cursor.getInt(2)
                val A: String = cursor.getString(3)
                val B: String = cursor.getString(4)
                val C: String = cursor.getString(5)
                val D: String = cursor.getString(6)
                val result: String = cursor.getString(7)
                listQuestions.add(QuestionModel(id, question, image, A, B, C, D, result))
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listQuestions
    }

    fun getQuestionsTheory(i: Int): List<QuestionModel> {
        val listQuestions = ArrayList<QuestionModel>()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery(
                "SELECT * FROM CauHoi WHERE id BETWEEN ${(((i - 1) * 25) + 1)} AND ${(i * 25)}",
                null
            )
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id: Int = cursor.getInt(0)
                val question: String = cursor.getString(1)
                val image: Int = cursor.getInt(2)
                val A: String = cursor.getString(3)
                val B: String = cursor.getString(4)
                val C: String = cursor.getString(5)
                val D: String = cursor.getString(6)
                val result: String = cursor.getString(7)
                listQuestions.add(QuestionModel(id, question, image, A, B, C, D, result))
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listQuestions
    }
}
