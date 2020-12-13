package com.example.internapp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.internapp.BlankFragment
import com.example.internapp.R

private val TAB_TITLES = arrayOf(
    "HOME",
    "ONDERNEMEN",
    "TECH",
    "FINANCE",
    "HISTORY",
    "GEOGRAPHY"
)


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        if(position==0){
            return PlaceholderFragment()
        }
        else{
            return BlankFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return 6
    }
}