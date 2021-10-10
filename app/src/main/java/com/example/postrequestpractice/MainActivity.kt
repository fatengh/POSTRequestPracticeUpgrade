package com.example.postrequestpractice
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var responseText: TextView
    private lateinit var btnadd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        responseText = findViewById(R.id.tView)
        btnadd = findViewById(R.id.btnadd)

        val apiinter = ApiClient().getClient()?.create(ApiInterface::class.java)


        if (apiinter != null) {
            apiinter.getUser()?.enqueue(object : Callback<List<Users.UserDeta>> {
                override fun onResponse(
                    call: Call<List<Users.UserDeta>>,
                    response: Response<List<Users.UserDeta>>
                ) {

                    var datas: String? = "";
                    for (User in response.body()!!) {
                        datas = datas + "${User.name} \n ${User.location} \n \n"
                    }
                    responseText.text = datas
                }

                override fun onFailure(call: Call<List<Users.UserDeta>>, th: Throwable) {
                    Toast.makeText(applicationContext, "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
                }
            })
        }

        btnadd.setOnClickListener {
            intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}