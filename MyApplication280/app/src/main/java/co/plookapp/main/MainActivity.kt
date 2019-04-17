package co.plookapp.main

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import co.plookapp.main.Model.Master
import co.plookapp.main.Model.MasterService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dialog.view.*
import kotlinx.android.synthetic.main.layout_item.view.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    var rest = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add.setOnClickListener {

            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Add Master")
            var modal = layoutInflater.inflate(R.layout.layout_dialog,null)
            alertDialog.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->

                rest.setMaster(modal.editTXT.text.toString())
                var intent = Intent(this,this@MainActivity::class.java)
                startActivity(intent)
            })
            alertDialog.setView(modal)
            alertDialog.show()
        }

        doAsync {

            activityUiThread {

                Thread.sleep(2000)

                getData()
            }
        }


    }

    fun getData(){

        var masterService : MasterService = rest.getMaster()

        var call = masterService.getMaster()

        call.enqueue(object : Callback<List<Master>> {
            override fun onFailure(call: Call<List<Master>>, t: Throwable) {
                Log.i("error", t.toString())
            }

            override fun onResponse(call: Call<List<Master>>, response: Response<List<Master>>) {

                response.body()!!.forEach {at ->

                    var item = layoutInflater.inflate(R.layout.layout_item,null)

                    item.nameTXT.setText(at.name)

                    item.btn_edit.setOnClickListener {et ->

                        var alertDialog = AlertDialog.Builder(this@MainActivity)

                        var modal = layoutInflater.inflate(R.layout.layout_dialog,null)

                        alertDialog.setTitle("Edit Master")

                        alertDialog.setView(modal)

                        modal.editTXT.setText(at.name)

                        alertDialog.setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->

                            rest.editMaster(at.id.toString(),modal.editTXT.text.toString())
                            var intent = Intent(this@MainActivity,this@MainActivity::class.java)
                            startActivity(intent)
                        })

                        alertDialog.show()

                    }

                    item.btn_delete.setOnClickListener {ut ->

                        rest.deleteMaster(at.id)
                        var intent = Intent(this@MainActivity,this@MainActivity::class.java)
                        startActivity(intent)

                    }

                    scContent.addView(item)
                }
            }

        })
    }

}
