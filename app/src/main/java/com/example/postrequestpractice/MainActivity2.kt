package com.example.postrequestpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    private lateinit var id: EditText
    private lateinit var name: EditText
    private lateinit var location: EditText
    private lateinit var btnSave: Button
    private lateinit var btnView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        id = findViewById(R.id.etId)
        name = findViewById(R.id.etName)
        location = findViewById(R.id.etLocation)
        btnSave = findViewById(R.id.btnSave)
        btnView = findViewById(R.id.btnView)

        btnSave.setOnClickListener {
            var f = Users.UserDeta( name.text.toString(),location.text.toString(), id.text.toString().toInt())
            val apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)

            if (apiInterface != null) {
                apiInterface.addUser(f).enqueue(object : Callback<Users.UserDeta> {
                    override fun onResponse(
                        call: Call<Users.UserDeta>,
                        response: Response<Users.UserDeta>
                    ) {
                        name.setText("")
                        location.setText("")
                        Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT)
                            .show();

                    }

                    override fun onFailure(call: Call<Users.UserDeta>, t: Throwable) {
                        name.setText("")
                        location.setText("")
                        Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();

                    }
                })
            }


        }

        btnView.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }


}