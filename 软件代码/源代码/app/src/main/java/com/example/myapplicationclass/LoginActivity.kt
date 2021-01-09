package com.example.myapplicationclass

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.database.sqlite.SQLiteDatabase
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    var userID :Int = 0
    lateinit var username :String
    lateinit var name: String
    lateinit var password : String
    lateinit var edt_Username :EditText
    lateinit var edt_Password :EditText
    lateinit var btn_Login :Button
    lateinit var btn_To_Register :Button

    private lateinit var DBHelper: MyDatabaseHelper
    private  fun initUI() {
        edt_Username = findViewById<View>(R.id.edt_username) as EditText
        edt_Password = findViewById<View>(R.id.edt_password) as EditText
        btn_Login = findViewById<View>(R.id.btn_login) as Button
        btn_To_Register = findViewById<View>(R.id.btn_to_register) as Button
        DBHelper = MyDatabaseHelper(this,"class.db",1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUI()

        btn_To_Register.setOnClickListener {
            val intent = Intent(this, RegistActivity::class.java)
            startActivityForResult(intent, 1)
        }

        btn_Login.setOnClickListener {
            name = edt_Username.text.toString()
            password = edt_Password.text.toString()
            if(login(name, password)) {
                Toast.makeText(this@LoginActivity, "登录成功$name", Toast.LENGTH_SHORT).show()
                object : Thread() {
                    override fun run() {
                        try
                        {
                            sleep(1000)
                            val intent1 = Intent(this@LoginActivity, MainActivity2::class.java)
                            val userid0=userID.toString()
                            intent1.putExtra("userID", userid0)

                            startActivity(intent1)
                            finish()
                        }
                        catch (e: Exception)
                        {
                            e.printStackTrace()
                        }
                    }
                }.start()
            }
            else{
                Toast.makeText(this@LoginActivity, "账号不存在或密码错误$name", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun login(name:String, password:String) :Boolean{
        var db = DBHelper.writableDatabase
        val sql = "select userID from User where name=? and password=?"
        val cursor: Cursor = db.rawQuery(sql, arrayOf(name, password))
        if (cursor.count != 0) {//返回Cursor中的行数,不等于0即能匹配
            if (cursor.moveToNext()) {
                userID = cursor.getInt(cursor.getColumnIndex("userID"));
                username = name
            }
            cursor.close();
            return true;
        }

        return false;
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == 1) {
            val name = data?.getStringExtra("name")
            val password = data?.getStringExtra("password")
            edt_username.setText(name)
            edt_password.setText(password)
        }
    }
}