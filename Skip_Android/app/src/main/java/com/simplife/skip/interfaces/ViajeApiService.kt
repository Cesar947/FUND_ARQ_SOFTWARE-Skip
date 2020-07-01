package com.simplife.skip.interfaces

import com.simplife.skip.models.Viaje
import retrofit2.Call
import retrofit2.http.GET

interface ViajeApiService {

    @GET("api/auth/viajes")
    fun getViajes(): Call<List<Viaje>>
}