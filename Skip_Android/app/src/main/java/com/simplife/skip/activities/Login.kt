package com.simplife.skip.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.simplife.skip.*
import com.simplife.skip.interfaces.UsuarioApiService
import com.simplife.skip.models.LoginEntity
import com.simplife.skip.models.LoginRequest
import com.simplife.skip.models.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var userEmail: EditText
    private lateinit var userPass: EditText
    private lateinit var userService: UsuarioApiService
    private lateinit var homeP : Intent
    var id: Long = 0
    var rol = "pasajero"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.login_button)
        userEmail = findViewById(R.id.user_email)
        userPass = findViewById(R.id.user_pass)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.6:6060/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userService = retrofit.create(UsuarioApiService::class.java)



        loginBtn.setOnClickListener{
            val loginRequest = LoginRequest(userEmail.text.toString(), userPass.text.toString())

            var token = userEmail.text.toString() + userPass.text.toString()

            userService.getAllUsers(loginRequest).enqueue(object : Callback<LoginEntity> {
                override fun onResponse(call: Call<LoginEntity>?, response: Response<LoginEntity>?) {

                    var usuario = response?.body()

                    if (usuario != null) {
                        id = usuario.usuarioId
                        rol = usuario.roles[0]

                        homeP = Intent(applicationContext, MainActivity::class.java).putExtra("userid", id)
                        startActivity(homeP)
                        finish()
                    }
                    else{
                        Toast.makeText(this@Login,"Ingrese un correo y contraseña existente",Toast.LENGTH_SHORT).show();}
                }

                override fun onFailure(call: Call<LoginEntity>?, t: Throwable?) {
                    t?.printStackTrace()
                }
            })



        }

    }


    //Logica Reciclable de login
    fun login(email: String, pass: String){


        //Se reemplazaria por metodo de getusuario de bakcken
    }
}
