package com.simplife.skip.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.simplife.skip.R
import com.simplife.skip.models.Usuario
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_viaje_detail.*

class Post : AppCompatActivity() {

    private lateinit var backbtn: ImageButton
    private lateinit var postBtn: Button
    private lateinit var text: EditText
    private lateinit var origen: EditText
    private lateinit var destino: EditText
    private lateinit var origen_hora: EditText
    private lateinit var destino_hora: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val usuario = intent.getSerializableExtra("user") as Usuario

        postBtn = findViewById(R.id.post_btn)
        backbtn = findViewById(R.id.postback_button)
        text = findViewById(R.id.post_text)
        origen = findViewById(R.id.post_origen)
        destino = findViewById(R.id.post_destino)
        origen_hora = findViewById(R.id.post_hora_origen)
        destino_hora = findViewById(R.id.post_hora_destino)

        post_author.setText(usuario.username)

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(applicationContext)
            .applyDefaultRequestOptions(requestOptions)
            .load(usuario.image)
            .into(post_image)


        backbtn.setOnClickListener{
            finish()
        }

        postBtn.setOnClickListener {
            finish()
        }


    }
}
