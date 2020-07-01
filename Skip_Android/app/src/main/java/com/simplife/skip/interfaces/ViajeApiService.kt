package com.simplife.skip.interfaces

import com.simplife.skip.models.Viaje
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ViajeApiService {

    @GET("api/auth/skip/viajes")
    fun getViajes(): Call<List<Viaje>>

    @GET("api/auth/skip/viajes/{id}")
    fun getViajeById(@Path("id") id: Long): Call<Viaje>

    @GET("api/auth/skip/viajes/conductor/{conductorid}")
    fun getViajesDeConductor(@Path("conductorid") conductorid: Long): Call<List<Viaje>>
}