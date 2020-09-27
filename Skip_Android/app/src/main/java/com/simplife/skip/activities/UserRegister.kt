package com.simplife.skip.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplife.skip.R
import com.simplife.skip.interfaces.UsuarioApiService
import com.simplife.skip.util.URL_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRegister : AppCompatActivity() {




    private lateinit var userService: UsuarioApiService


    private lateinit var prefs : SharedPreferences
    private lateinit var edit: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        edit= prefs.edit()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userService = retrofit.create(UsuarioApiService::class.java)


    }
}