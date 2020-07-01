package com.simplife.skip.interfaces

import com.simplife.skip.models.LoginEntity
import com.simplife.skip.models.LoginRequest
import com.simplife.skip.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioApiService {

    @POST("api/auth/usuario/login")
    fun getAllUsers(@Body loginRequest: LoginRequest): Call<LoginEntity>

    @GET("api/auth/skip/usuarios/{id}")
    fun getUsuarioById(@Path("id") id: Long): Call<Usuario>

}