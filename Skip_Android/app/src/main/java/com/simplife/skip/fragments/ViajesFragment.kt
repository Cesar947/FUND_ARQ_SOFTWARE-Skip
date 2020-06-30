package com.simplife.skip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.simplife.skip.R
import com.simplife.skip.adapter.MisViajeCondRecyclerAdapter
import com.simplife.skip.adapter.MisViajesRecyclerAdapter
import com.simplife.skip.models.Usuario
import com.simplife.skip.util.TopSpacingItemDecoration

/**
 * A simple [Fragment] subclass.
 */
class ViajesFragment : Fragment() {

    private  lateinit var misviajesAdapterPasajero: MisViajesRecyclerAdapter
    private lateinit var misviajesAdapterConductor: MisViajeCondRecyclerAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val vista = inflater.inflate(R.layout.fragment_viajes, container, false)

        //Recibimos data de usuario
        val usuario: Usuario? = arguments?.get("user") as Usuario?

        recyclerView = vista.findViewById(R.id.recycler_misviajes_view)
        recyclerView.layoutManager = LinearLayoutManager(context)


        if(usuario?.id == 1 as Long){
           misviajesAdapterConductor = MisViajeCondRecyclerAdapter()
            recyclerView.adapter = misviajesAdapterConductor
            val data1 = com.simplife.skip.util.DataSource.createDataSet()
            misviajesAdapterConductor.submitList(data1)
        }
        else{
            misviajesAdapterPasajero = MisViajesRecyclerAdapter()
            recyclerView.adapter = misviajesAdapterPasajero
            val data2 = com.simplife.skip.util.DataSource.createDataSet()
            misviajesAdapterPasajero.submitList(data2)
        }

        return vista
    }

    private fun addDataSet(){

    }

}
