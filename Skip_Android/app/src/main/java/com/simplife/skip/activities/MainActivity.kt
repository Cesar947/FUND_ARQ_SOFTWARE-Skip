package com.simplife.skip.activities

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.simplife.skip.R
import com.simplife.skip.adapter.PagerViewAdapter
import com.simplife.skip.models.Usuario

class MainActivity : AppCompatActivity() {


    private lateinit var homebtn: ImageButton
    private lateinit var viajesbtn: ImageButton
    private lateinit var notifbtn: ImageButton
    private lateinit var perfilbtn: ImageButton
    private lateinit var buscarbtn: ImageButton

    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerViewAdapter: PagerViewAdapter

    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usuario = intent.getSerializableExtra("user") as Usuario

        bundle = Bundle()
        bundle.putSerializable("user", usuario)

        mViewPager = findViewById(R.id.viewPager)

        homebtn = findViewById(R.id.homebtn)
        viajesbtn = findViewById(R.id.carbtn)
        notifbtn = findViewById(R.id.notifbtn)
        perfilbtn = findViewById(R.id.profilebtn)
        buscarbtn = findViewById(R.id.searchbtn)

        homebtn.setOnClickListener {
            mViewPager.currentItem = 0

        }

        buscarbtn.setOnClickListener {
            mViewPager.currentItem = 1

        }

        viajesbtn.setOnClickListener {

            mViewPager.currentItem = 2

        }

        notifbtn.setOnClickListener {
            mViewPager.currentItem = 3

        }

        perfilbtn.setOnClickListener {
            mViewPager.currentItem = 4

        }


        mPagerViewAdapter = PagerViewAdapter(supportFragmentManager, bundle)
        mViewPager.adapter = mPagerViewAdapter
        //mPagerViewAdapter.bundle


        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                changeTabs(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        mViewPager.currentItem = 0
        homebtn.setImageResource(R.drawable.ic_home_selected)

    }

    private fun changeTabs(position: Int) {


        if (position == 0) {
            homebtn.setImageResource(R.drawable.ic_home_selected)
            buscarbtn.setImageResource(R.drawable.ic_search)
            viajesbtn.setImageResource(R.drawable.ic_car)
            notifbtn.setImageResource(R.drawable.ic_notifications)
            perfilbtn.setImageResource(R.drawable.ic_account)
        }
        if (position == 1) {
            homebtn.setImageResource(R.drawable.ic_home_gray)
            buscarbtn.setImageResource(R.drawable.ic_search_selected)
            viajesbtn.setImageResource(R.drawable.ic_car)
            notifbtn.setImageResource(R.drawable.ic_notifications)
            perfilbtn.setImageResource(R.drawable.ic_account)
        }
        if (position == 2) {
            homebtn.setImageResource(R.drawable.ic_home_gray)
            buscarbtn.setImageResource(R.drawable.ic_search)
            viajesbtn.setImageResource(R.drawable.ic_car_selected)
            notifbtn.setImageResource(R.drawable.ic_notifications)
            perfilbtn.setImageResource(R.drawable.ic_account)
        }
        if (position == 3) {
            homebtn.setImageResource(R.drawable.ic_home_gray)
            buscarbtn.setImageResource(R.drawable.ic_search)
            viajesbtn.setImageResource(R.drawable.ic_car)
            notifbtn.setImageResource(R.drawable.ic_notifications_selected)
            perfilbtn.setImageResource(R.drawable.ic_account)
        }
        if (position == 4) {
            homebtn.setImageResource(R.drawable.ic_home_gray)
            buscarbtn.setImageResource(R.drawable.ic_search)
            viajesbtn.setImageResource(R.drawable.ic_car)
            notifbtn.setImageResource(R.drawable.ic_notifications)
            perfilbtn.setImageResource(R.drawable.ic_account_selected)
        }

    }

}




