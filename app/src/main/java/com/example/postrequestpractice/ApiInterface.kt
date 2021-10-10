package com.example.postrequestpractice

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiInterface {

    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getUser(): Call<List<Users.UserDeta>>


    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addUser(@Body userData: Users.UserDeta): Call<Users.UserDeta>

}