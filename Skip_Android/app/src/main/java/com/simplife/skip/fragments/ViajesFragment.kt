package com.simplife.skip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.simplife.skip.R
import com.simplife.skip.adapter.MisViajesRecyclerAdapter
import com.simplife.skip.util.TopSpacingItemDecoration

/**
 * A simple [Fragment] subclass.
 */
class ViajesFragment : Fragment() {

    private  lateinit var miviajeAdapter: MisViajesRecyclerAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val vista = inflater.inflate(R.layout.fragment_viajes, container, false)

        recyclerView = vista.findViewById(R.id.recycler_misviajes_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val topSpacingDecoration = TopSpacingItemDecoration(30)
        recyclerView.addItemDecoration(topSpacingDecoration)
        miviajeAdapter = MisViajesRecyclerAdapter()
        recyclerView.adapter = miviajeAdapter
        val data1 = com.simplife.skip.util.DataSource.createDataSet()
        miviajeAdapter.submitList(data1)

        return vista
    }

    private fun addDataSet(){

    }

}
