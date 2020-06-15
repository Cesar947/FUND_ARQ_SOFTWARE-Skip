package com.simplife.skip.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.simplife.skip.fragments.*

internal class PagerViewAdapter(fm:FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                HomeFragment()
            }
            1->{
                SearchFragment()
            }
            2->{
                ViajesFragment()
            }
            3->{
                NotificacionFragment()
            }
            4->{
                PerfilFragment()
            }
            else -> HomeFragment()
        }
    }

    override fun getCount(): Int {
        return 5
    }

}