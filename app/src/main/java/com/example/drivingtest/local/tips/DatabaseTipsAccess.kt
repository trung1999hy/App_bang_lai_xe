package com.example.drivingtest.local.tips

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.drivingtest.model.TipModel

@Suppress("CAST_NEVER_SUCCEEDS")
class DatabaseTipsAccess private constructor(val context: Context) {
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

    fun getTheoreticalTips(): List<TipModel> {
        val getTheoreticalTips: MutableList<TipModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM MEO", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val kind = cursor.getInt(1)
                val content = cursor.getString(2)
                if (kind == 0) getTheoreticalTips.add(TipModel(id, kind, content))
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }

        return getTheoreticalTips as ArrayList
    }

    fun getTipsNoticeBoard(): List<TipModel> {
        val listTips: MutableList<TipModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM MEO", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val kind = cursor.getInt(1)
                val content = cursor.getString(2)
                if (kind == 1) listTips.add(TipModel(id, kind, content))
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listTips as ArrayList
    }

    fun getTipsPicture(): List<TipModel> {
        val listTips: MutableList<TipModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM MEO", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val kind = cursor.getInt(1)
                val content = cursor.getString(2)
                val meoGhiNho = TipModel(id, kind, content)
                if (kind == 2) listTips.add(meoGhiNho)
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return listTips as ArrayList
    }
}