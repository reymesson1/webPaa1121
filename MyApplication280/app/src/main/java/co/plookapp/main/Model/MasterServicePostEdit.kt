package co.plookapp.main.Model

import org.json.JSONObject
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body

interface MasterServicePostEdit{

    @POST("editwalletandroid")

    fun editMaster(@Body data : JSONObject) : Call<String>

}