package com.example.myapplicationclass

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //测试数据，合并后要在登录时取数据name和password，从数据库搜索class_name
    companion object{
        val name="小明"
        val password="123456"
        val class_name:String=""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val mTitleList = ArrayList<String>()
        mTitleList.add("作业")
        mTitleList.add("通知")
        val mFragmentList = ArrayList<Fragment>()
        mFragmentList.add(workfragment())
        mFragmentList.add(notefragment())
        val adapter=sub_frag_Adapter(supportFragmentManager,mFragmentList,mTitleList)
        mviewpager.adapter=adapter
        mytab.setupWithViewPager(mviewpager)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addwork->{
                Toast.makeText(this,"添加作业", Toast.LENGTH_SHORT).show()
                val intent=Intent(this,Add_work::class.java)
                startActivity(intent)}
            R.id.addnote->{
                Toast.makeText(this,"添加通知", Toast.LENGTH_SHORT).show()
                val intent= Intent(this,Add_note::class.java)
                startActivity(intent)}
            R.id.addclass->{
                Toast.makeText(this,"添加班级", Toast.LENGTH_SHORT).show()
                val intent=Intent(this,Add_class::class.java)
                startActivity(intent)}
        }
        return true
    }
}
