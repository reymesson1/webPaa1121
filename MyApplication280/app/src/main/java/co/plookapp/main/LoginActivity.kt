package co.plookapp.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var rest = RestAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {

            rest.setLogin(username.text.toString(),password.text.toString())

            if(RestAPI.token.length>0&&RestAPI.account.equals("private")) {

                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else if(RestAPI.token.length>0&&RestAPI.account.equals("public")) {

                var intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }else{

                Toast.makeText(this,"Username or Password incorrect", Toast.LENGTH_LONG).show()
            }
        }
    }
}