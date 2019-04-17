package co.plookapp.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_item.view.*
import kotlinx.android.synthetic.main.layout_item_second.view.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity() {

    var rest = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        doAsync {

            activityUiThread {

                Thread.sleep(2000)

                getData()
            }
        }

    }

    fun getData(){

        var developmentService : DevelopmentService = rest.getDevelopment()

        var call = developmentService.getDevelopment()

        call.enqueue(object : Callback<List<Development>>{
            override fun onFailure(call: Call<List<Development>>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(call: Call<List<Development>>, response: Response<List<Development>>) {

                Log.i("response", response.body().toString())

                response.body()!!.forEach {at->

                    var item = layoutInflater.inflate(R.layout.layout_item_second, null)

                    item.nameTXTSecond.setText(at.name)

                    item.btn_show.setOnClickListener {et ->

                        var intent = Intent(this@SecondActivity,ThirdActivity::class.java)
                        intent.putExtra("selected",at.name)
                        startActivity(intent)

                    }

                    scContent.addView(item)
                }
            }
        })
    }
}