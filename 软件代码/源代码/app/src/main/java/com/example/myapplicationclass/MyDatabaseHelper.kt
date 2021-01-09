package com.example.myapplicationclass

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context?,name:String,version:Int):SQLiteOpenHelper(context,name,null,version) {
    private val createWork="create table Work("+"title text,"+"con text,"+"class_name text)"
    private val createNote="create table Note("+"title text,"+"con text,"+"class_name text)"
    private val createUser="create table User("+"userID integer primary key autoincrement,"+"name text,"+"password text,"+"class_name text)"
    private val createClass="create table Class("+"class_name text,"+"c_password text)"
    private val createBook = "create table course ("+
            "user_id integer ,"+
            "name text,"+
            "start_time integer," +
            "end_time integer," +
            "start_week integer,"+
            "end_week integer," +
            "teacher_name text,"+
            "day integer)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createWork)
        db.execSQL(createNote)
        db.execSQL(createUser)
        db.execSQL(createClass)
        db.execSQL(createBook)
        Toast.makeText(context,"创建成功",Toast.LENGTH_SHORT).show()

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}


