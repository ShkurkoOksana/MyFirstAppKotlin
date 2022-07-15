package com.bignerdranch.android.notebookexample.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.bignerdranch.android.notebookexample.model.Item

class MyDBManager(context: Context) {
    val myDBHelper = MyDBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB() {
        db = myDBHelper.writableDatabase
    }

    fun insertToDB(title: String, desc: String, uri: String) {
        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_TITLE, title)
            put(MyDBNameClass.COLUMN_NAME_CONTENT, desc)
            put(MyDBNameClass.COLUMN_NAME_IMAGE_URI, uri)
        }

        db?.insert(MyDBNameClass.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDBData(): ArrayList<Item> {
        val dataList = ArrayList<Item>()
        val cursor = db?.query(MyDBNameClass.TABLE_NAME, null, null, null, null, null, null)

        while (cursor?.moveToNext()!!) {
            val dataTitle = cursor.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_TITLE))
            val dataDesc = cursor.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_CONTENT))
            val dataImageUri = cursor.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_IMAGE_URI))
            val item = Item()
            item.title = dataTitle
            item.desc = dataDesc
            item.imageUri = dataImageUri

            dataList.add(item)
        }

        cursor.close()
        return dataList
    }

    fun closeDB() {
        myDBHelper.close()
    }
}