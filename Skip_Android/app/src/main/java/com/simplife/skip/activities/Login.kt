package com.simplife.skip.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.simplife.skip.*
import com.simplife.skip.models.Usuario
import com.simplife.skip.util.UserData

class Login : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var userEmail: EditText
    private lateinit var userPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.login_button)
        userEmail = findViewById(R.id.user_email)
        userPass = findViewById(R.id.user_pass)



        loginBtn.setOnClickListener{
            login(userEmail.text.toString(), userPass.text.toString())
        }

    }


    //Logica Reciclable de login
    fun login(email: String, pass: String){

        //Datos de usuarios de prubea
        val data_usuarios = UserData.createUsers()


        //Se reemplazaria por metodo de getusuario de bakckend
        val conductor: Usuario = data_usuarios.get(0)
        val pasajero: Usuario = data_usuarios.get(1)

        val homeP : Intent
        val homeC : Intent

        if (email == "pepino@gmail.com" && pass == "chino") {
            homeP = Intent(this, MainActivity::class.java).putExtra("user", pasajero)
            startActivity(homeP)
            finish()
        }
        if (email == "cesar@gmail.com" && pass == "1234"){
            homeC = Intent(this, MainActivity::class.java).putExtra("user", conductor)
            startActivity(homeC)
            finish()
        }
    }
}
