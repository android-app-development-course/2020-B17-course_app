package com.example.myapplicationclass

import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_login.*


class MainActivity2 : AppCompatActivity() {
    //private val dbHelper = MyDatabaseHelper(this,"course.db",1)
    private var gridHeight = 0
    private var gridWidth = 0
    var userid:Int=0
//    val userid= intent.getIntExtra("userID",0)
    private var layout: RelativeLayout? = null
    private var tmpLayout: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        userid= intent.getStringExtra("userID")!!.toInt()

        tmpLayout = findViewById<View>(R.id.Monday) as RelativeLayout

        loaddata()
        /*  course_column.setOnClickListener{
              when(it){
                  Monday -> Toast.makeText(this,Monday.course.text,Toast.LENGTH_SHORT).show()
              }
          }*/

    }
    private fun loaddata(){

        val dbHelper = MyDatabaseHelper(this,"class.db",1)
        val db = dbHelper.readableDatabase
        if(db!=null) {
            //val cursor = db.query("course", null, "user_id", arrayOf(userid), null, null, null)
            val sql = "select name,start_time,end_time,start_week,end_week,teacher_name,day from course where user_id=?"
            Log.d("data",userid.toString())
            val cursor: Cursor = db.rawQuery(sql, arrayOf(userid.toString()))
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val start_time = cursor.getInt(cursor.getColumnIndex("start_time"))
                    val end_time = cursor.getInt(cursor.getColumnIndex("end_time"))
                    val start_week = cursor.getInt(cursor.getColumnIndex("start_week"))
                    val end_week = cursor.getInt(cursor.getColumnIndex("end_week"))
                    val teacher_name = cursor.getString(cursor.getColumnIndex("teacher_name"))
                    val day = cursor.getInt(cursor.getColumnIndex("day"))
                    Log.d("course",name)
                    addView(day, start_time, end_time, name)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (isFirst) {
            isFirst = false
            gridWidth = tmpLayout!!.width
            gridHeight = tmpLayout!!.height / 12
        }
    }

    private fun createTv(start: Int, end: Int, text: String): TextView {
        val tv = TextView(this)
        /*
         指定高度和宽度
         */
        val params = LinearLayout.LayoutParams(gridWidth, gridHeight * (end - start + 1))
        /*
        指定位置
         */tv.y = gridHeight * (start - 1).toFloat()
        tv.layoutParams = params
        tv.gravity = Gravity.CENTER
        tv.text = text
        return tv
    }

    private fun addView(i: Int, start: Int, end: Int, text: String) {
        val tv: TextView
        when (i) {
            1 -> layout = findViewById<View>(R.id.Monday) as RelativeLayout
            2 -> layout = findViewById<View>(R.id.Tuesday) as RelativeLayout
            3 -> layout = findViewById<View>(R.id.Wednesday) as RelativeLayout
            4 -> layout = findViewById<View>(R.id.Thursday) as RelativeLayout
            5 -> layout = findViewById<View>(R.id.Friday) as RelativeLayout
            6 -> layout = findViewById<View>(R.id.Saturday) as RelativeLayout
            7 -> layout = findViewById<View>(R.id.Sunday) as RelativeLayout
        }
        tv = createTv(start, end, text)
        tv.setBackgroundColor(Color.argb(100, start * 5, (start + end) * 20, 50))
        layout!!.addView(tv)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        /*if (id == R.id.action_settings) {
            val text = "移动智能开发"
            addView(1, 1, 2, text)
            addView(7, 2, 3, text)
            addView(5, 9, 10, text)
            addView(4, 2, 3, text)
            addView(3, 5, 5, text)
            addView(4, 10, 12, text)
            return true
        }*/
        if (id == R.id.action_settings)
            loaddata()
        else if(id == R.id.add_course){
            showDialog()

        }
        else if (id ==R.id.add_course_from_web){
            val intent1 = Intent(this, Web::class.java)
            startActivity(intent1)
        }
        if (id==R.id.myclass){
            val intent2 = Intent(this,MainActivity::class.java)
            startActivity(intent2)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private var isFirst = true
    }
    private fun showDialog() {
        val dbHelper = MyDatabaseHelper(this,"class.db",1)
        val db = dbHelper.writableDatabase
        val dialog = Dialog(this)
        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog .setCancelable(true)
        dialog .setContentView(R.layout.activity_course)
        //val body = dialog .findViewById(R.id.course_name) as TextView
        val inputName = dialog.findViewById(R.id.course_name) as EditText
        //body.text = title
        val start = dialog.findViewById(R.id.TimeofcourseBegin) as Spinner
        val end = dialog.findViewById(R.id.TimeofcourseEnd) as Spinner
        val start_week = dialog.findViewById(R.id.WeeksofcourseBegin) as Spinner
        val end_week = dialog.findViewById(R.id.WeeksofcourseEnd) as Spinner
        val day = dialog.findViewById(R.id.course_of_which_day) as Spinner
        val teacher_name = dialog.findViewById(R.id.teacher_name) as EditText
        val yesBtn = dialog .findViewById(R.id.yesBtn) as Button
        val noBtn = dialog .findViewById(R.id.noBtn) as Button
        yesBtn.setOnClickListener {
            val name = inputName.text.toString()
            val start_time = start.selectedItem.toString().toInt()
            val end_time = end.selectedItem.toString().toInt()
            val week_s = start_week.selectedItem.toString().toInt()
            val week_e = end_week.selectedItem.toString().toInt()
            val t_name = teacher_name.text.toString()
            val day = day.selectedItem.toString().toInt()
            val values = ContentValues().apply {
                put("user_id",userid)
                put("name",name)
                put("start_time",start_time)
                put("end_time",end_time)
                put("start_week",week_s)
                put("end_week",week_e)
                put("teacher_name",t_name)
                put("day",day)
            }
            db.insert("course",null,values)
            //Toast.makeText(this,name,Toast.LENGTH_SHORT).show()
            addView(day,start_time,end_time,name)
            dialog .dismiss()

        }
        noBtn.setOnClickListener { dialog .dismiss() }
        dialog .show()

    }
}