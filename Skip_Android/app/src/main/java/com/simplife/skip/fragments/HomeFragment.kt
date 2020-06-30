package com.simplife.skip.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.simplife.skip.R
import com.simplife.skip.activities.Post
import com.simplife.skip.activities.ViajeDetail
import com.simplife.skip.adapter.ViajeRecyclerAdapter
import com.simplife.skip.interfaces.UsuarioApiService
import com.simplife.skip.models.Usuario
import com.simplife.skip.util.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.sql.DataSource

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private  lateinit var viajeAdapter: ViajeRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addBtn: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val vista = inflater.inflate(R.layout.fragment_home, container, false)
        addBtn = vista.findViewById(R.id.add_btn)

        //Recibimos data de usuario
        val usuarioid = arguments?.get("user") as Long

        val id: Long = 1

        if(usuarioid == id){
            addBtn.visibility = View.VISIBLE
        }

        addBtn.setOnClickListener{
            context?.startActivity(Intent(context, Post::class.java).putExtra("user", usuarioid))
        }

        recyclerView = vista.findViewById(R.id.recycler_viaje_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        //val topSpacingDecoration = TopSpacingItemDecoration(30)
        //recyclerView.addItemDecoration(topSpacingDecoration)
        viajeAdapter = ViajeRecyclerAdapter()
        recyclerView.adapter = viajeAdapter
        val data = com.simplife.skip.util.DataSource.createDataSet()
        viajeAdapter.submitList(data)


        return vista
    }

    private fun addDataSet(){

    }


}


