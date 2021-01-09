package com.example.myapplicationclass

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import kotlinx.android.synthetic.main.frag_work.*

class notefragment:ListFragment() {
    private val noteList=ArrayList<message>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contentView=inflater.inflate(R.layout.frag_note,container,false)

        val noteadapter=messageAdapter2()
        this.listAdapter=noteadapter
        return contentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initnotes()
        val dbHelper=MyDatabaseHelper(activity,"class.db",1)
        this.list.setOnItemClickListener { parent, view, position, id ->
            val note=noteList[position]
            Toast.makeText(activity,note.mess_title+note.mess_con, Toast.LENGTH_SHORT).show()
            val intent= Intent(activity,details::class.java)
            intent.putExtra("data_title",note.mess_title)
            intent.putExtra("data_con",note.mess_con)
            intent.putExtra("w_n","note")
            startActivity(intent)
        }
    }
    private fun initnotes(){
        noteList.clear()
//        noteList.add(message("考试","第18周"))
//        noteList.add(message("物理","第三章习题12，24，25"))
//        noteList.add(message("高数","第五章习题6，7，10"))
//        noteList.add(message("线代","第一章习题21，25，30"))
        val dbHelper=MyDatabaseHelper(activity,"class.db",1)
        val db=dbHelper.writableDatabase
        //val cursor=db.query("Note",null,"class_name=?", arrayOf(MainActivity.class_name),null,null,null)
        val cursor=db.query("User",null,null, null,null,null,null)
        if(cursor.moveToFirst()){
            do {
                //val title=cursor.getString(cursor.getColumnIndex("title"))
                //val con=cursor.getString(cursor.getColumnIndex("con"))
                val title=cursor.getString(cursor.getColumnIndex("userID"))
                val con=cursor.getString(cursor.getColumnIndex("password"))
                noteList.add(message(title,con))
            }while (cursor.moveToNext())
        }
        cursor.close()
    }
    inner class messageAdapter2(): BaseAdapter(){
        override fun getCount(): Int {
            return noteList.size
        }

        override fun getItem(position: Int): message {
            return noteList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view:View
            if(convertView==null){
                view=LayoutInflater.from(context).inflate(R.layout.message_item,parent,false)
            }
            else{
                view=convertView
            }
            val mess=getItem(position)
            val mess_title: TextView =view.findViewById(R.id.textView_m_t)
            val mess_content: TextView =view.findViewById(R.id.textView_m_c)
            if(mess!=null){
                mess_title.text=mess.mess_title
                mess_content.text=mess.mess_con
            }
            return view
        }
    }
}