package com.simplife.skip.interfaces

import com.simplife.skip.models.Viaje
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ViajeApiService {

    @GET("api/auth/viajes")
    fun getViajes(): Call<List<Viaje>>

    @GET("api/auth/viajes/{id}")
    fun getViajeById(@Path("id") id: Int): Call<Viaje>
}