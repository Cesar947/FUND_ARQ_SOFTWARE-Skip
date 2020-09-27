package com.simplife.skip.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
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
import com.simplife.skip.util.URL_API
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
    var rol = ""


    private lateinit var prefs : SharedPreferences
    private lateinit var edit: SharedPreferences.Editor

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val vista = inflater.inflate(R.layout.fragment_viajes, container, false)

        prefs = activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
        edit= prefs.edit()

        rol = prefs.getString("rol","Nada")!!
        Log.i("Rol",rol)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        //Recibimos data de usuario
        var usuarioid = prefs.getLong("idusuario",0)


        viajeService = retrofit.create(ViajeApiService::class.java)

        recyclerView = vista.findViewById(R.id.recycler_misviajes_view)
        recyclerView.layoutManager = LinearLayoutManager(context)


        if(rol == "ROL_CONDUCTOR"){
            misviajesAdapterConductor = MisViajeCondRecyclerAdapter()
            recyclerView.adapter = misviajesAdapterConductor
        }
        else
        {
            misviajesAdapterPasajero = MisViajesRecyclerAdapter()
            recyclerView.adapter = misviajesAdapterPasajero
        }


        viajeService.getViajesDeConductor(usuarioid).enqueue(object :Callback<List<Viaje>>{
            override fun onResponse(call: Call<List<Viaje>>, response: Response<List<Viaje>>) {
                val viajes = response.body()
                Log.i("Viajes",viajes.toString())
                if(viajes!= null)
                {
                    if (rol == "ROL_CONDUCTOR") {
                        misviajesAdapterConductor = MisViajeCondRecyclerAdapter()
                        recyclerView.adapter = misviajesAdapterConductor
                        misviajesAdapterConductor.submitList(viajes)
                        Log.i("Viajes","Es conductor")
                    }
                    else
                    {
                        misviajesAdapterPasajero = MisViajesRecyclerAdapter()
                        recyclerView.adapter = misviajesAdapterPasajero
                        misviajesAdapterPasajero.submitList(viajes)
                        Log.i("Viajes","Es pasajero")
                    }
                }
            }
            override fun onFailure(call: Call<List<Viaje>>, t: Throwable) {
                Log.e("Viajes","Error al obtener viajes")
            }
        })

        /*viajeService!!.getViajesDeConductor(usuarioid).enqueue(object: Callback<List<Viaje>> {
            override fun onResponse(call: Call<List<Viaje>>, response: Response<List<Viaje>>) {
                val viajesaux = response.body()
                Log.i("ViajesAAAAAAAAAAAAA",response.body().toString())
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
        })*/

        return vista
    }

    private fun addDataSet(){

    }

}
