package com.example.drivingtest.local.tips

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.drivingtest.model.TipsModel

@Suppress("CAST_NEVER_SUCCEEDS")
class DatabaseTipsAccess private constructor(context: Context) {
    private val openHelper: SQLiteOpenHelper = DatabaseTipsHelper(context)
    private var database: SQLiteDatabase? = null

    companion object {
        private var instance: DatabaseTipsAccess? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseTipsAccess {
            if (instance == null) {
                instance = DatabaseTipsAccess(context)
            }
            return instance as DatabaseTipsAccess
        }
    }

    private fun openDatabase() {
        database = openHelper.readableDatabase
    }

    private fun closeDatabase() {
        database?.close()
    }

    fun getMemorizationTips(): List<TipsModel> {
        val listMemorizationTips: MutableList<TipsModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM MEO", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val kind = cursor.getInt(1)
                val content = cursor.getString(2)
                listMemorizationTips.add(TipsModel(id, kind, content))
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listMemorizationTips
    }

    fun getTheoreticalTips(): List<TipsModel> {
        val listMemorizationTips: MutableList<TipsModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM MEO", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val kind = cursor.getInt(1)
                val content = cursor.getString(2)
                if (kind == 0) listMemorizationTips.add(TipsModel(id, kind, content))
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }

        return listMemorizationTips
    }

    fun getTipsNoticeBoard(): List<TipsModel> {
        val listMeoGhiNho: MutableList<TipsModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM MEO", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val kind = cursor.getInt(1)
                val content = cursor.getString(2)
                if (kind == 1) listMeoGhiNho.add(TipsModel(id, kind, content))
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listMeoGhiNho
    }

    fun getTipsPicture(): List<TipsModel> {
        val listMeoGhiNho: MutableList<TipsModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor : Cursor = it.rawQuery("SELECT * FROM MEO", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val kind = cursor.getInt(1)
                val content = cursor.getString(2)
                val meoGhiNho = TipsModel(id, kind, content)
                if (kind == 2) listMeoGhiNho.add(meoGhiNho)
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listMeoGhiNho
    }
}