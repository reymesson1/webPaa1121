package co.plookapp.main

import retrofit2.http.GET
import retrofit2.Call

interface DevelopmentService{

    @GET("developments")

    fun getDevelopment() : Call<List<Development>>

}