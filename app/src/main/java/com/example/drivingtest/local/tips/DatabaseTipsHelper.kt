package com.example.drivingtest.local.tips

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseTipsHelper(context: Context) :
    SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "Tips.sqlite"
        private const val DATABASE_VERSION = 1
    }
}