package com.simplife.skip.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.simplife.skip.R
import com.simplife.skip.interfaces.UsuarioApiService
import com.simplife.skip.models.Usuario
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_viaje_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Post : AppCompatActivity() {

    private lateinit var backbtn: ImageButton
    private lateinit var postBtn: Button
    private lateinit var text: EditText
    private lateinit var origen: EditText
    private lateinit var destino: EditText
    private lateinit var origen_hora: EditText
    private lateinit var destino_hora: EditText

    private lateinit var usuarioService: UsuarioApiService

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

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.6:6060/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        usuarioService = retrofit.create(UsuarioApiService::class.java)

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
            finish()
        }


    }
}
