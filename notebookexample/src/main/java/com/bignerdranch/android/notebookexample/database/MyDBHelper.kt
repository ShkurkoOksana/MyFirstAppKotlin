package com.bignerdranch.android.notebookexample.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(
    context, MyDBNameClass.DATABASE_NAME, null, MyDBNameClass.DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MyDBNameClass.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(MyDBNameClass.SQL_DELETE_TABLE)
        onCreate(db)
    }
}