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
import com.simplife.skip.interfaces.ViajeApiService
import com.simplife.skip.models.Usuario
import com.simplife.skip.models.Viaje
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
    private lateinit var viajeService: ViajeApiService
    private lateinit var usuarioService: UsuarioApiService

    var usuario: Usuario? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val vista = inflater.inflate(R.layout.fragment_home, container, false)
        addBtn = vista.findViewById(R.id.add_btn)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.6:6060/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        viajeService = retrofit.create(ViajeApiService::class.java)
        usuarioService = retrofit.create(UsuarioApiService::class.java)

        //Recibimos data de usuario
        val usuarioid = arguments?.get("user") as Long

        usuarioService.getUsuarioById(usuarioid).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>?, response: Response<Usuario>?) {
                val respuesta = response?.body()
                usuario = respuesta


                if(usuario?.cuenta?.roles?.get(0)?.nombre == "ROL_CONDUCTOR"){
                    addBtn.visibility = View.VISIBLE
                }


            }
            override fun onFailure(call: Call<Usuario>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })


        addBtn.setOnClickListener{
            context?.startActivity(Intent(context, Post::class.java).putExtra("user", usuarioid))
        }

        recyclerView = vista.findViewById(R.id.recycler_viaje_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viajeService!!.getViajes().enqueue(object: Callback<List<Viaje>> {
            override fun onResponse(call: Call<List<Viaje>>, response: Response<List<Viaje>>) {
                val viajesaux = response.body()

                var sorted = viajesaux!!.sortedWith(compareByDescending ({it.id}))

                viajeAdapter = ViajeRecyclerAdapter()
                recyclerView.adapter = viajeAdapter
                viajeAdapter.submitList(viajesaux)


            }
            override fun onFailure(call: Call<List<Viaje>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })




        return vista
    }

    private fun addDataSet(){

    }


}


