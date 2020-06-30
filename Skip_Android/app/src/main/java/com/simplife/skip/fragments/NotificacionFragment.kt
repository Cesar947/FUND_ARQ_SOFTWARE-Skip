package com.simplife.skip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.simplife.skip.R
import com.simplife.skip.adapter.SolicitudesRecyclerAdapter
import com.simplife.skip.models.Usuario
import com.simplife.skip.util.SolisData

/**
 * A simple [Fragment] subclass.
 */
class NotificacionFragment : Fragment() {

    private  lateinit var solicitudesAdapter: SolicitudesRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_notificacion, container, false)

        val usuarioid = arguments?.get("user") as Long

        recyclerView = vista.findViewById(R.id.recycler_notificaciones_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        solicitudesAdapter = SolicitudesRecyclerAdapter()
        recyclerView.adapter = solicitudesAdapter
        val data1 = SolisData.createResenas()
        solicitudesAdapter.submitList(data1)

        return vista
    }

}
