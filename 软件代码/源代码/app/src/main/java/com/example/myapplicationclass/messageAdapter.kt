package com.example.myapplicationclass

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class messageAdapter(context: Context, val resourceId:Int, data:List<message>):ArrayAdapter<message>(context,resourceId,data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View
        if(convertView==null){
            view= LayoutInflater.from(context).inflate(resourceId,parent,false)
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