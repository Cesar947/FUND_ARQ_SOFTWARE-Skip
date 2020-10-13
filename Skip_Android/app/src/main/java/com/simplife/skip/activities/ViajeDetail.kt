package com.simplife.skip.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import com.simplife.skip.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.simplife.skip.adapter.ResenasRecyclerAdapter
import com.simplife.skip.interfaces.SolicitudApiService
import com.simplife.skip.interfaces.ViajeApiService
import com.simplife.skip.models.LoginEntity
import com.simplife.skip.models.Solicitud
import com.simplife.skip.models.SolicitudRequest
import com.simplife.skip.models.Viaje
import com.simplife.skip.util.Resenas_Data
import com.simplife.skip.util.URL_API
import kotlinx.android.synthetic.main.activity_viaje_detail.*
import kotlinx.android.synthetic.main.viaje_list_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViajeDetail : AppCompatActivity() {

    private lateinit var back_btn: ImageButton

    private  lateinit var viajeAdapter: ResenasRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var viajeService: ViajeApiService
    private lateinit var solicitudService: SolicitudApiService


    private lateinit var btn_solicitar : Button

    var viaje: Viaje? = null
    var viajeid = 0L


    private lateinit var prefs : SharedPreferences
    private lateinit var edit: SharedPreferences.Editor

    private lateinit var mapFragment :SupportMapFragment

    @SuppressLint("MissingPermission")
    private val mapCallback = OnMapReadyCallback{ googleMap ->
        googleMap.isMyLocationEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = true

        val Lima = LatLng(-12.0554671, -77.0431111)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Lima, 11.0f))

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viaje_detail)

        viajeid = intent.getSerializableExtra("via") as Long
        back_btn = findViewById(R.id.detback_button)

        prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        edit= prefs.edit()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        viajeService = retrofit.create(ViajeApiService::class.java)
        solicitudService = retrofit.create(SolicitudApiService::class.java)


        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        btn_solicitar = findViewById(R.id.btn_solicitar)


        viajeService.getViajeById(viajeid).enqueue(object : Callback<Viaje> {
            override fun onResponse(call: Call<Viaje>?, response: Response<Viaje>?) {
                val respuesta = response?.body()
                viaje = respuesta

                viajedetail_author.setText(viaje?.conductor?.nombres)
                viajedetail_title.setText(viaje?.fechaPublicacion)
                viajedetail_text.setText(viaje?.descripcion)
                viajedetail_destino.setText(viaje?.conductor?.sede)
                //viajedetail_origen.setText(viaje?.conductor?.ubicacion)
                viajedetail_hora_destino.setText(viaje?.horaLlegada)
                viajedetail_hora_origen.setText(viaje?.horaInicio)

                Glide.with(applicationContext)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(viaje?.conductor?.imagen)
                    .into(viajedetail_image)


            }
            override fun onFailure(call: Call<Viaje>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })



        //Lista de Resenas
        recyclerView = findViewById(R.id.recycler_resenas_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        //val topSpacingDecoration = TopSpacingItemDecoration(30)
        //recyclerView.addItemDecoration(topSpacingDecoration)
        viajeAdapter = ResenasRecyclerAdapter()
        recyclerView.adapter = viajeAdapter
        val data = Resenas_Data.createResenas()
        viajeAdapter.submitList(data)
        //Fin de lista de Resenas


        back_btn.setOnClickListener{
            finish()
        }
        btn_solicitar.setOnClickListener {
            solicitarViaje()
        }

        mapFragment = supportFragmentManager.findFragmentById(R.id.mapViajeDetail) as SupportMapFragment
        mapFragment?.getMapAsync(mapCallback)

    }





    fun solicitarViaje()
    {

        var solicitd = SolicitudRequest(mensaje = "Llevame po favo",pasajeroId = prefs.getLong("idusuario",0L), viajeId = viajeid, paradaEncuentroId = 1)

        solicitudService.solicitarViaje(solicitd).enqueue(object : Callback<Solicitud> {
            override fun onResponse(call: Call<Solicitud>?, response: Response<Solicitud>?) {
                val soli = response!!.body()
                Log.i("Solicitud",soli.toString())
                finish()
            }
            override fun onFailure(call: Call<Solicitud>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

}
