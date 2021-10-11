package com.example.postrequestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity3 : AppCompatActivity() {
    private lateinit var id: EditText
    private lateinit var name: EditText
    private lateinit var location: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        id = findViewById(R.id.etId)
        name = findViewById(R.id.etName)
        location = findViewById(R.id.etLocation)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)




        btnUpdate.setOnClickListener {
            val id = id.text.toString().toInt()
            val name = name.text.toString()
            val location = location.text.toString()
            val apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)

            apiInterface!!.UpdateUser(id,Users.UserDeta(name,location,id)).enqueue(object : Callback<Users.UserDeta> {
                override fun onResponse(call: Call<Users.UserDeta>, response: Response<Users.UserDeta>) {
                    Toast.makeText(applicationContext, "User Updated", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Users.UserDeta>, t: Throwable) {
                    Toast.makeText(applicationContext, "User Not Updated", Toast.LENGTH_SHORT).show()
                }
                })
            }

        btnDelete.setOnClickListener {

            val idtodeleted = id.text.toString().toInt()


            val apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)

            apiInterface!!.deleteUser(idtodeleted).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(applicationContext, "User Deleted", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "User Not Found", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}
