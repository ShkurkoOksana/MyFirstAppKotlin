package com.bignerdranch.android.notebookexample.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.KeyEvent
import com.bignerdranch.android.notebookexample.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyDBManager(context: Context) {
    val myDBHelper = MyDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = myDBHelper.writableDatabase
    }

    suspend fun insertToDB(title: String, desc: String, uri: String, time: String) = withContext(Dispatchers.IO) {
        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_TITLE, title)
            put(MyDBNameClass.COLUMN_NAME_CONTENT, desc)
            put(MyDBNameClass.COLUMN_NAME_IMAGE_URI, uri)
            put(MyDBNameClass.COLUMN_NAME_TIME, time)
        }

        db?.insert(MyDBNameClass.TABLE_NAME, null, values)
    }

    fun removeItemFromDB(id: String) {
        var selection = BaseColumns._ID + "=$id"

        db?.delete(MyDBNameClass.TABLE_NAME, selection, null)
    }

    suspend fun updateItemFromDB(id: Int, title: String, desc: String, uri: String, time: String) = withContext(Dispatchers.IO) {
        var selection = BaseColumns._ID + "=$id"

        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_TITLE, title)
            put(MyDBNameClass.COLUMN_NAME_CONTENT, desc)
            put(MyDBNameClass.COLUMN_NAME_IMAGE_URI, uri)
            put(MyDBNameClass.COLUMN_NAME_TIME, time)
        }

        db?.update(MyDBNameClass.TABLE_NAME, values, selection, null)
    }

    @SuppressLint("Range")
    suspend fun readDBData(searchText: String): ArrayList<Item> = withContext(Dispatchers.IO) {
        val dataList = ArrayList<Item>()
        val selection = "${MyDBNameClass.COLUMN_NAME_TITLE} like ?"
        val cursor = db?.query(MyDBNameClass.TABLE_NAME, null, selection, arrayOf("%$searchText%"), null, null, null)

        while (cursor?.moveToNext()!!) {
            val dataId = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val dataTitle = cursor.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_TITLE))
            val dataDesc = cursor.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_CONTENT))
            val dataImageUri = cursor.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_IMAGE_URI))
            val dataTime = cursor.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_TIME))
            val item = Item()
            item.id = dataId
            item.title = dataTitle
            item.desc = dataDesc
            item.imageUri = dataImageUri
            item.time = dataTime

            dataList.add(item)
        }

        cursor.close()
        return@withContext dataList
    }

    fun closeDB() {
        myDBHelper.close()
    }
}