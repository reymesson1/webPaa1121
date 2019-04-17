package co.plookapp.main.Model

import retrofit2.http.GET
import retrofit2.Call

interface MasterService{

    @GET("wallet")

    fun getMaster() : Call<List<Master>>

}