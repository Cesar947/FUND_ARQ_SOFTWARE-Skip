package com.simplife.skip.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.simplife.skip.R
import com.simplife.skip.interfaces.UsuarioApiService
import com.simplife.skip.interfaces.ViajeApiService
import com.simplife.skip.models.Parada
import com.simplife.skip.models.Usuario
import com.simplife.skip.models.Viaje
import com.simplife.skip.models.ViajeRequest
import com.simplife.skip.util.URL_API
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_viaje_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random.Default.nextInt

class Post : AppCompatActivity() {

    private lateinit var backbtn: ImageButton
    private lateinit var postBtn: Button
    private lateinit var description: EditText
    private lateinit var origen: EditText
    private lateinit var destino: EditText
    private lateinit var origen_hora: EditText
    private lateinit var destino_hora: EditText
    private lateinit var fecha_viaje: EditText

    private lateinit var origenSpinner:Spinner

    private lateinit var usuarioService: UsuarioApiService
    lateinit var viajeService: ViajeApiService

    var viaje: Viaje? = null

    var usuario: Usuario? = null

    private  lateinit var mylocation : LatLng


    private lateinit var locationRequest  : LocationRequest
    private lateinit var locationCallback  : LocationCallback
    private lateinit var mFusedLocationClient  : FusedLocationProviderClient


    private lateinit var prefs : SharedPreferences
    private lateinit var edit: SharedPreferences.Editor


    private var markerOrigen : Marker? = null
    private var markerDestino : Marker? = null


    @SuppressLint("MissingPermission")
    private val mapCallback = OnMapReadyCallback{ googleMap ->
        googleMap.isMyLocationEnabled = true
        googleMap.uiSettings.isMyLocationButtonEnabled = true
        googleMap.uiSettings.isMapToolbarEnabled = true

        val Lima = LatLng(-12.0554671, -77.0431111)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Lima, 11.0f))

        //-12.077252,-77.0934926 - UPC San Miguel

        googleMap.setOnMapClickListener {
            if (markerOrigen!= null &&  markerDestino != null)
            {
                return@setOnMapClickListener
            }
            val markerOptions = MarkerOptions().position(it).anchor(0.5f,0.5f).flat(false).draggable(true)

            if (markerOrigen == null)
            {
                markerOptions.title("Origen")
                markerOrigen = googleMap.addMarker(markerOptions)
                //origen.setText(markerOrigen!!.position.toString())
            }
            else
            {
                markerOptions.title("Destino")
                markerDestino = googleMap.addMarker(markerOptions)
                destino.setText(markerDestino!!.position.toString())

            }
        }
    }


    @SuppressLint("MissingPermission")
    private fun traerUbicacion(){

        locationRequest = LocationRequest.create()
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest.setInterval(20 * 1000)
        locationCallback = object : LocationCallback() {

            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult == null) {
                    return;
                }
                for (location in locationResult.getLocations()) {
                    if (location != null) {
                        mylocation = LatLng(location.latitude, location.longitude)
                    }
                }
                super.onLocationResult(locationResult)
            }
        }
        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        edit= prefs.edit()

        val usuarioid = prefs.getLong("idusuario",0)



        postBtn = findViewById(R.id.post_btn)
        backbtn = findViewById(R.id.postback_button)
        description = findViewById(R.id.post_description)
        //origen = findViewById(R.id.post_origen)
        origenSpinner = findViewById(R.id.origen_spinner)
        destino = findViewById(R.id.post_destino)
        origen_hora = findViewById(R.id.post_hora_origen)
        destino_hora = findViewById(R.id.post_hora_destino)
        fecha_viaje = findViewById(R.id.fechaProgrmada)



        val array = arrayListOf("San Miguel","San Isidro","Moterrico")
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,array)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        origenSpinner.adapter = adapter


        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapPostViaje) as SupportMapFragment
        mapFragment?.getMapAsync(mapCallback)


        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        usuarioService = retrofit.create(UsuarioApiService::class.java)
        viajeService = retrofit.create(ViajeApiService::class.java)

        usuarioService.getUsuarioById(usuarioid).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>?, response: Response<Usuario>?) {
                val respuesta = response?.body()
                usuario = respuesta

                post_author.setText(usuario?.nombres)

                Glide.with(applicationContext)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(usuario?.imagen)
                    .into(post_image)
            }
            override fun onFailure(call: Call<Usuario>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })

        backbtn.setOnClickListener{
            finish()
        }

        postBtn.setOnClickListener {
            publicarViaje(usuarioid)
            finish()
        }


    }

    fun publicarViaje(usuarioid: Long){
        val random1 =  ThreadLocalRandom.current().nextInt(0, 10000000).toFloat()
        val random2 =  ThreadLocalRandom.current().nextInt(0, 10000000).toFloat()
        val random3 =  ThreadLocalRandom.current().nextInt(0, 10000000).toFloat()
        val random4 =  ThreadLocalRandom.current().nextInt(0, 10000000).toFloat()

        val distancia: Int = 20000

        var inicio: Parada = Parada(origen.text.toString(), random1, random2)
        var fin: Parada = Parada(destino.text.toString(), random3,random4)

        Log.i("Pepino dice lo que se envia: ", inicio.toString())

        var viajeRequest: ViajeRequest = ViajeRequest(usuarioid, true, inicio, fin, "2 horas",
            distancia.toFloat() , description.text.toString(), fecha_viaje.text.toString(), origen_hora.text.toString(), destino_hora.text.toString())

        Log.i("Pepino dice lo que se envia: ", viajeRequest.toString())

        viajeService.publicarViaje(viajeRequest).enqueue(object : Callback<Viaje> {
            override fun onResponse(call: Call<Viaje>?, response: Response<Viaje>?) {
                viaje = response?.body()

                Log.i("Pepino dice: ", viaje.toString())

            }
            override fun onFailure(call: Call<Viaje>?, t: Throwable?) {
                t?.printStackTrace()
            } })

    }
}
