package com.simplife.skip.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.simplife.skip.R
import com.simplife.skip.interfaces.UsuarioApiService
import com.simplife.skip.interfaces.ViajeApiService
import com.simplife.skip.models.Parada
import com.simplife.skip.models.Usuario
import com.simplife.skip.models.Viaje
import com.simplife.skip.models.ViajeRequest
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
    private lateinit var text: EditText
    private lateinit var origen: EditText
    private lateinit var destino: EditText
    private lateinit var origen_hora: EditText
    private lateinit var destino_hora: EditText
    private lateinit var fecha_viaje: EditText

    private lateinit var usuarioService: UsuarioApiService
    lateinit var viajeService: ViajeApiService

    var viaje: Viaje? = null

    var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val usuarioid = intent.getSerializableExtra("user") as Long

        postBtn = findViewById(R.id.post_btn)
        backbtn = findViewById(R.id.postback_button)
        text = findViewById(R.id.post_text)
        origen = findViewById(R.id.post_origen)
        destino = findViewById(R.id.post_destino)
        origen_hora = findViewById(R.id.post_hora_origen)
        destino_hora = findViewById(R.id.post_hora_destino)
        fecha_viaje = findViewById(R.id.fechaProgrmada)

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.6:6060/")
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
            distancia.toFloat() , text.text.toString(), fecha_viaje.text.toString(), origen_hora.text.toString(), destino_hora.text.toString())

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
