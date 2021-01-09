package com.example.myapplicationclass

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*

class details : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar_details)
        val data_t=intent.getStringExtra("data_title")
        val data_c=intent.getStringExtra("data_con")
        val w_n=intent.getStringExtra("w_n")
        details_title.setText(data_t)
        details_con.setText(data_c)
        val dbHelper=MyDatabaseHelper(this,"class.db",1)

        if(w_n=="work"){
            button_change.setOnClickListener{
                //val t=details_title.text.toString()
                val c=details_con.text.toString()
                val db=dbHelper.writableDatabase
                val values=ContentValues()
                values.put("con",c)
                db.update("Work",values,"title=?", arrayOf(data_t))
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            button_del.setOnClickListener {
                val db=dbHelper.writableDatabase
                db.delete("Work","title=? and con=?", arrayOf(data_t,data_c))
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        if(w_n=="note"){
            button_change.setOnClickListener{
                val c=details_con.text.toString()
                val db=dbHelper.writableDatabase
                val values=ContentValues()
                values.put("con",c)
                db.update("Note",values,"title=?", arrayOf(data_t))
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            button_del.setOnClickListener {
                val db=dbHelper.writableDatabase
                db.delete("Note","title=? and con=?", arrayOf(data_t,data_c))
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}

