package com.example.postrequestpractice

import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getUser(): Call<List<Users.UserDeta>>


    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addUser(@Body userData: Users.UserDeta): Call<Users.UserDeta>

    @Headers("Content-Type: application/json")
    @PUT("/test/{id}")
    fun UpdateUser(@Path( "id")id: Int, @Body  userData: Users.UserDeta): Call<Users.UserDeta>


    @Headers("Content-Type: application/json")
    @DELETE ("/test/{id}")
    fun deleteUser(@Path("id")id:Int):Call<Void>


}