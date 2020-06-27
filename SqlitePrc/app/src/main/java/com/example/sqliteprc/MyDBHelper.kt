package com.example.sqliteprc

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context : Context) : SQLiteOpenHelper(context,name,null,version){
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE if not exists myTABLE (id integer PRIMARY KEY AUTOINCREMENT,book text NOT NULL, price integer NOT NULL)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS myTABLE")
        onCreate(db)
    }

    companion object{
        //資料庫名稱
        val name = "mdatabase.bd"
        //資料庫版本,資料結構改變的時候要更改這個數字,通常是加一
        val version = 1
    }
}