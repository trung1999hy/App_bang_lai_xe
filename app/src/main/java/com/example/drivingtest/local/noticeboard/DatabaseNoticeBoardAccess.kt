package com.example.drivingtest.local.noticeboard

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.drivingtest.model.TrafficSignModel

class DatabaseNoticeBoardAccess private constructor(context: Context) {
    private val openHelper: SQLiteOpenHelper = DatabaseNoticeBoardHelper(context)
    private var database: SQLiteDatabase? = null

    companion object {
        private var instance: DatabaseNoticeBoardAccess? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseNoticeBoardAccess {
            if (instance == null) {
                instance = DatabaseNoticeBoardAccess(context)
            }
            return instance as DatabaseNoticeBoardAccess
        }
    }

    private fun openDatabase() {
        database = openHelper.readableDatabase
    }

    private fun closeDatabase() {
        database?.close()
    }

    fun getListDangerSign(): List<TrafficSignModel> {
        val listNoticeBoard: MutableList<TrafficSignModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM BIENBAO WHERE loaibien = 1", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val image = cursor.getString(0)
                val content = cursor.getString(1)
                val typeNoticeBoard = cursor.getInt(2)
                if (image.compareTo("0") != 0) {
                    listNoticeBoard.add(TrafficSignModel(content, image, typeNoticeBoard))
                }
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listNoticeBoard
    }

    fun getListForbiddenSign(): List<TrafficSignModel> {
        val listNoticeBoard: MutableList<TrafficSignModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM BIENBAO WHERE loaibien = 2", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val image = cursor.getString(0)
                val content = cursor.getString(1)
                val typeNoticeBoard = cursor.getInt(2)
                if (image.compareTo("0") != 0) {
                    listNoticeBoard.add(TrafficSignModel(content, image, typeNoticeBoard))
                }
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listNoticeBoard
    }

    fun getListCommandSignboard(): List<TrafficSignModel> {
        val listNoticeBoard: MutableList<TrafficSignModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM BIENBAO WHERE loaibien = 3", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val image = cursor.getString(0)
                val content = cursor.getString(1)
                val typeNoticeBoard = cursor.getInt(2)
                if (image.compareTo("0") != 0) {
                    listNoticeBoard.add(TrafficSignModel(content, image, typeNoticeBoard))
                }
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listNoticeBoard
    }

    fun getListDirectionalSignboard(): List<TrafficSignModel> {
        val listNoticeBoard: MutableList<TrafficSignModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM BIENBAO WHERE loaibien = 4", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val image = cursor.getString(0)
                val content = cursor.getString(1)
                val typeNoticeBoard = cursor.getInt(2)
                if (image.compareTo("0") != 0) {
                    listNoticeBoard.add(TrafficSignModel(content, image, typeNoticeBoard))
                }
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listNoticeBoard
    }

    fun getListAuxiliarySignboard(): List<TrafficSignModel> {
        val listNoticeBoard: MutableList<TrafficSignModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM BIENBAO WHERE loaibien = 5", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val image = cursor.getString(0)
                val content = cursor.getString(1)
                val typeNoticeBoard = cursor.getInt(2)
                if (image.compareTo("0") != 0) {
                    listNoticeBoard.add(TrafficSignModel(content, image, typeNoticeBoard))
                }
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listNoticeBoard
    }
}