package com.simplife.skip.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.simplife.skip.fragments.*

internal class PagerViewAdapter(fm:FragmentManager?, bundle: Bundle) : FragmentPagerAdapter(fm!!) {

    private lateinit var fragment: Fragment

    private var frbundle: Bundle = bundle

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                fragment = HomeFragment()
                fragment.arguments = frbundle
                return fragment
            }
            1->{
                fragment = SearchFragment()
                return fragment
            }
            2->{
                fragment = ViajesFragment()
                return fragment
            }
            3->{
                fragment = NotificacionFragment()
                return fragment
            }
            4->{
                fragment = PerfilFragment()
                return fragment
            }
            else -> HomeFragment()
        }
    }

    override fun getCount(): Int {
        return 5
    }

}