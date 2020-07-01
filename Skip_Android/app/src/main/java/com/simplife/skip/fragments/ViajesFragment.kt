package com.simplife.skip.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.simplife.skip.R
import com.simplife.skip.adapter.MisViajeCondRecyclerAdapter
import com.simplife.skip.adapter.MisViajesRecyclerAdapter
import com.simplife.skip.adapter.ViajeRecyclerAdapter
import com.simplife.skip.interfaces.UsuarioApiService
import com.simplife.skip.interfaces.ViajeApiService
import com.simplife.skip.models.Usuario
import com.simplife.skip.models.Viaje
import com.simplife.skip.util.TopSpacingItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class ViajesFragment : Fragment() {

    private  lateinit var misviajesAdapterPasajero: MisViajesRecyclerAdapter
    private lateinit var misviajesAdapterConductor: MisViajeCondRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viajeService: ViajeApiService
    private lateinit var usuarioService: UsuarioApiService

    var usuario: Usuario? = null
    var validacion: String = "Pasajero"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val vista = inflater.inflate(R.layout.fragment_viajes, container, false)

        //Recibimos data de usuario
        val usuarioid = arguments?.get("user") as Long

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.6:6060/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        viajeService = retrofit.create(ViajeApiService::class.java)
        usuarioService = retrofit.create(UsuarioApiService::class.java)

        recyclerView = vista.findViewById(R.id.recycler_misviajes_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        usuarioService.getUsuarioById(usuarioid).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>?, response: Response<Usuario>?) {
                val respuesta = response?.body()
                usuario = respuesta


                if(usuario?.cuenta?.roles?.get(0)?.nombre == "ROL_CONDUCTOR"){
                    misviajesAdapterConductor = MisViajeCondRecyclerAdapter()
                    recyclerView.adapter = misviajesAdapterConductor
                    validacion = "Conductor"
                }
                else{
                    validacion = "Pasajero"

                }
            }
            override fun onFailure(call: Call<Usuario>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })

        viajeService!!.getViajesDeConductor(usuarioid).enqueue(object: Callback<List<Viaje>> {
            override fun onResponse(call: Call<List<Viaje>>, response: Response<List<Viaje>>) {
                val viajesaux = response.body()

                if(validacion == "Conductor"){
                    if (viajesaux != null) {
                        misviajesAdapterConductor.submitList(viajesaux)
                    }
                }
                else{
                    if (viajesaux != null) {
                        misviajesAdapterPasajero = MisViajesRecyclerAdapter()
                        recyclerView.adapter = misviajesAdapterPasajero
                        misviajesAdapterPasajero.submitList(viajesaux)
                    }

                }


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
