package com.simplife.skip.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.simplife.skip.fragments.HomeFragment
import com.simplife.skip.fragments.NotificacionFragment
import com.simplife.skip.fragments.PerfilFragment
import com.simplife.skip.fragments.ViajesFragment

internal class PagerViewAdapter(fm:FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                HomeFragment()
            }
            1->{
                ViajesFragment()
            }
            2->{
                NotificacionFragment()
            }
            3->{
                PerfilFragment()
            }
            else -> HomeFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

}