package com.example.drivingtest.local.tips

import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.drivingtest.local.Preference
import com.example.drivingtest.model.TipsModel

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

    fun getTheoreticalTips(): List<TipsModel> {
        val getTheoreticalTips: MutableList<TipsModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM MEO", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val kind = cursor.getInt(1)
                val content = cursor.getString(2)
                if (kind == 0) getTheoreticalTips.add(TipsModel(id, kind, content))
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }

        return getData(context, getTheoreticalTips as ArrayList)
    }

    fun getTipsNoticeBoard(): List<TipsModel> {
        val listTips: MutableList<TipsModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM MEO", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val kind = cursor.getInt(1)
                val content = cursor.getString(2)
                if (kind == 1) listTips.add(TipsModel(id, kind, content))
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return getData(context, listTips as ArrayList)
    }

    fun getTipsPicture(): List<TipsModel> {
        val listTips: MutableList<TipsModel> = ArrayList()
        openDatabase()
        database?.let {
            val cursor: Cursor = it.rawQuery("SELECT * FROM MEO", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val kind = cursor.getInt(1)
                val content = cursor.getString(2)
                val meoGhiNho = TipsModel(id, kind, content)
                if (kind == 2) listTips.add(meoGhiNho)
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
        }
        return getData(context, listTips as ArrayList)
    }

    private fun getData(
        context: Context,
        data: ArrayList<TipsModel>
    ): java.util.ArrayList<TipsModel> {
        val listPositionBlock = arrayListOf<TipsModel>()
        val listByeKey: Set<String> =
            Preference.buildInstance(context)?.getKeyUnlock() as Set<String>
        for (i in 0 until data.size) {
                if (listByeKey.isNotEmpty()) {
                    var checkLock = true
                    for (item in listByeKey) {
                        Log.d(TAG, "getData: $item | ${data[i]}")
                        if (data[i].id.toString() + "" == item) {
                            checkLock = false
                            break
                        }
                    }
                    listPositionBlock.add(
                        TipsModel(data[i].id, data[i].kind, data[i].content, checkLock)
                    )
                } else {
                    listPositionBlock.add(
                        TipsModel(data[i].id, data[i].kind, data[i].content, true)
                    )
                }
        }
        return listPositionBlock
    }
}