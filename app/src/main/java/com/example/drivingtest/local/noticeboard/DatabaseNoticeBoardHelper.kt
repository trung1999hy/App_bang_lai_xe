package com.example.drivingtest.local.noticeboard

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseNoticeBoardHelper(context: Context) :
    SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "SignBoard.sqlite"
        private const val DATABASE_VERSION = 1
    }
}