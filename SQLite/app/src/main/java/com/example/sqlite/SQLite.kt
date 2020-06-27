package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLite (context: Context) :  SQLiteOpenHelper(context,name, SQLiteDatabase.CursorFactory(),version){
    companion object{
        //資料庫名稱
        val name  = "mdatabase.db"
        //資料庫版本,資料結構改變的時候要更改這個數字，通常是+1
        val version = 1
    }

    override fun onCreate(db: SQLiteDatabase) {//資料名稱 , 資料型態 , 額外指令
        val sql = "CREATE TABLE if not exists myTABLE(book text PRIMARY KEY AUTOINCREMENT,  price integer NOT NULL)"
        db.execSQL(sql)
    }

    //刪除及更新表格動作
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS myTABLE")
    }
}