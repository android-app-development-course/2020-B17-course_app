package com.example.myapplicationclass

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistActivity : AppCompatActivity() {

    private lateinit var edt_setname :EditText
    private lateinit var edt_setpassword :EditText
    private lateinit var edt_confirm_password :EditText
    private lateinit var btn_register: Button
    lateinit var name :String
    lateinit var password :String
    lateinit var userid :String
    var contentValues = ContentValues()
    private lateinit var DBHelper: MyDatabaseHelper

    private  fun initUI() {
        edt_setname = findViewById<View>(R.id.edt_rg_username) as EditText
        edt_setpassword = findViewById<View>(R.id.edt_rg_password) as EditText
        edt_confirm_password = findViewById<View>(R.id.edt_c_password) as EditText
        btn_register = findViewById<View>(R.id.btn_regist) as Button
        DBHelper = MyDatabaseHelper(this,"class.db",1)
    }

    fun check(name: String): Boolean {
        val db: SQLiteDatabase = DBHelper.writableDatabase
        val sql = "select * from User where name=?"
        val cursor: Cursor = db.rawQuery(sql, arrayOf(name))
        if (cursor.count !== 0) {
            cursor.close()
            return true
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        initUI()
        DBHelper = MyDatabaseHelper(this, "class.db",1)
        btn_register.setOnClickListener {
            name = edt_setname.text.toString()
            password = edt_setpassword.text.toString()
            var db = DBHelper.writableDatabase


            //判断一波
            if (edt_setname.text.toString().contains(" ") || edt_setpassword.text.toString().contains(" ")) {
                Toast.makeText(this, "输入的用户名或密码不能包含空格", Toast.LENGTH_SHORT).show();
            }
            else if(edt_setpassword.getText().toString().isEmpty() || edt_setname.text.toString().isEmpty()
            ){
                Toast.makeText(this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
            }
            else if(edt_confirm_password.text.toString() != edt_setpassword.text.toString()){
                Toast.makeText(this, "两次密码不一致，请确认再注册", Toast.LENGTH_SHORT).show();
            }
            if(!check(name)){
                contentValues.put("name", name);
                contentValues.put("password", password);
//                contentValues.put("class_name","2班");

                var id_give = db.insert("User", null, contentValues)
                /**
                 * val sql = "select user_ID from ser where name=? and password=?"
                 * val cursor = db.rawQuery(sql, arrayOf(name, password))
                 * if (cursor.moveToNext()) { //id
                 *      userid = cursor.getInt(0).toString() //防止数据库的值null和0 分不清
                 * }
                 * cursor.close()
                 */

                Toast.makeText(this@RegistActivity, "注册成功", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                db.close()
                startActivityForResult(intent, 1)
                finish()
            }
            else{
                Toast.makeText(this, "该用户名已注册", Toast.LENGTH_SHORT).show()
            }

        }

    }
}