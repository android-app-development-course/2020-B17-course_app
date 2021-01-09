package com.example.myapplicationclass

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_work.*
import com.example.myapplicationclass.MainActivity

class Add_work : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_work)
        setSupportActionBar(toolbar_work)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)
        save_work.setOnClickListener {
            val db=dbHelper.writableDatabase
            val t=work_t.text.toString()
            val c=work_c.text.toString()
            val cla=MainActivity.class_name
            val value=ContentValues().apply {
                put("title",t)
                put("con",c)
                put("class_name",cla)
            }
            db.insert("Work",null,value)
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
