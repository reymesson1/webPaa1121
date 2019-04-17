package co.plookapp.main.Model

import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface MasterServicePostDelete{

    @POST("deletewalletandroid")

    fun deleteMaster(@Body data : JSONObject) : Call<String>

}