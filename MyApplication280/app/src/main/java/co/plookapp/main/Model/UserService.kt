package co.plookapp.main.Model

import com.google.gson.JsonElement
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface UserService{

    @POST("login")

    fun setLogin(@Body data : JSONObject) : Call<JsonElement>

}