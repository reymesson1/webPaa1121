package co.plookapp.main

import android.util.Log
import co.plookapp.main.Model.*
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RestAPI{

    companion object {

        var token = ""
        var account = ""
    }

    var retrofit = Retrofit.Builder()
        .baseUrl("http://190.94.2.105:8082/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMaster() : MasterService {

        return retrofit.create(MasterService::class.java)
    }

    fun setMaster(name:String){

        var newMaster = Master()
        newMaster.id = Date().time.toString()
        newMaster.date = Date().toString()
        newMaster.name = name

        var json = Gson().toJson(newMaster)

        var masterServicePost : MasterServicePost = retrofit.create(MasterServicePost::class.java)

        var call = masterServicePost.setMaster(JSONObject(json))

        call.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("response", response.body().toString())
            }
        })
    }

    fun setLogin(username:String,password:String){

        var newUser = User()
        newUser.id = Date().time.toString()
        newUser.date = Date().toString()
        newUser.username = username
        newUser.password = password

        var json = Gson().toJson(newUser)

        var userService : UserService = retrofit.create(UserService::class.java)

        var call = userService.setLogin(JSONObject(json))

        call.enqueue(object : Callback<JsonElement>{
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {

                Log.i("response", response.body().toString())

                var json : JsonObject = response.body()!!.asJsonObject

                token = json.get("token").toString().replace("\"","")
                account = json.get("account").toString().replace("\"","")
            }


        })

    }

    fun editMaster(id:String, name:String){

        var newMaster = Master()
        newMaster.id = id
        newMaster.name = name

        var json = Gson().toJson(newMaster)

        var masterServicePostEdit : MasterServicePostEdit = retrofit.create(MasterServicePostEdit::class.java)

        var call = masterServicePostEdit.editMaster(JSONObject(json))

        call.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("response", response.body().toString())
            }
        })

    }

    fun deleteMaster(id:String){

        var newMaster = Master()
        newMaster.id = id

        var json = Gson().toJson(newMaster)

        var masterServicePostDelete : MasterServicePostDelete = retrofit.create(MasterServicePostDelete::class.java)

        var call = masterServicePostDelete.deleteMaster(JSONObject(json))

        call.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("response", response.body().toString())
            }


        })

    }

    fun getDevelopment() : DevelopmentService{

        return retrofit.create(DevelopmentService::class.java)
    }

    fun setDevelopment(id:String, from :String){

        var newDevelopment = Development()
        newDevelopment.id = id
        newDevelopment.date = Date().toString()
        newDevelopment.from = from

        var json = Gson().toJson(newDevelopment)

        var developmentServicePost : DevelopmentServicePost = retrofit.create(DevelopmentServicePost::class.java)

        var call = developmentServicePost.setDevelopment(JSONObject(json))

        call.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("response", response.body().toString())
            }


        })

    }

}