package com.example.myapplicationclass

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class sub_frag_Adapter(fm:FragmentManager,val m_subFragment:ArrayList<Fragment>,val m_title:ArrayList<String>):FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return m_subFragment[position]
    }

    override fun getCount(): Int {
        return m_subFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return m_title[position]
    }

}