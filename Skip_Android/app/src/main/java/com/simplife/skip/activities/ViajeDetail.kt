package com.simplife.skip.activities

import com.simplife.skip.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.simplife.skip.adapter.ResenasRecyclerAdapter
import com.simplife.skip.interfaces.ViajeApiService
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

    var viaje: Viaje? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viaje_detail)

        val viajeid = intent.getSerializableExtra("via") as Long
        back_btn = findViewById(R.id.detback_button)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        viajeService = retrofit.create(ViajeApiService::class.java)

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

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


    }
}
