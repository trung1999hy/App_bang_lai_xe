package com.example.drivingtest.local.questions

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseQuestionHelper(context: Context) :
    SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "CauHoi.sqlite"
        private const val DATABASE_VERSION = 1
    }
}