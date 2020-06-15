package com.simplife.skip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.simplife.skip.R
import com.simplife.skip.adapter.ViajeRecyclerAdapter
import com.simplife.skip.util.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import javax.sql.DataSource

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private  lateinit var viajeAdapter: ViajeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = com.simplife.skip.util.DataSource.createDataSet()
        viajeAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        recycler_viaje_view.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            val topSpacingDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecoration)
            viajeAdapter = ViajeRecyclerAdapter()
            adapter = viajeAdapter
        }
    }

}
