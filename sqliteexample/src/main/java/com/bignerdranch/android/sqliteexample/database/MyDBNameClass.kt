package com.bignerdranch.android.sqliteexample.database

import android.provider.BaseColumns

object MyDBNameClass : BaseColumns {
    const val TABLE_NAME = "my_table"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MyLessonDB.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ${MyDBNameClass.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${MyDBNameClass.COLUMN_NAME_TITLE} TEXT," +
                "${MyDBNameClass.COLUMN_NAME_CONTENT} TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${MyDBNameClass.TABLE_NAME}"
}