package co.plookapp.main.Model

import org.json.JSONObject
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body

interface MasterServicePost{

    @POST("addwalletandroid")

    fun setMaster(@Body data : JSONObject) : Call<String>

}