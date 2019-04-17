package co.plookapp.main

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_third.*
import java.util.*

class ThirdActivity : AppCompatActivity() {

    var rest = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        titleThird.setText(intent.getStringExtra("selected"))

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
//        val hour = c.get(Calendar.HOUR_OF_DAY)
//        val minute = c.get(Calendar.MINUTE)

        var fecha : String = ""
        var tiempo : String = ""


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            editText.setText("" + dayOfMonth + "-" + monthOfYear + "-" + year)
            fecha = "" + dayOfMonth + "-" + monthOfYear + "-" + year
        }, year, month, day)

        editText.setOnFocusChangeListener { v, hasFocus ->

            dpd.show()
        }

        var hour : Int
        var minute : Int

        var timePickerDialog: TimePickerDialog? = null

        val setTimeListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minuteOfHour ->
            hour = hourOfDay
            minute = minuteOfHour

            editText2.setText(hour.toString() + ":"+minute.toString())
            tiempo = hour.toString() + ":"+minute.toString()
        }


        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        timePickerDialog = TimePickerDialog(this, setTimeListener, currentHour, currentMinute, true)

        editText2.setOnFocusChangeListener { v, hasFocus ->

            timePickerDialog?.show()

        }


        btn_save.setOnClickListener {

            rest.setDevelopment("test", fecha.toString() + " " + tiempo.toString() )

            var intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

        }


    }

}

