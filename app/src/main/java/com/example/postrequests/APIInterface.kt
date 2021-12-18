package com.example.postrequests

import android.text.Editable
import retrofit2.Call
import retrofit2.http.*

interface APIInterface{

    @GET("test/")
    fun getTest(): Call<ArrayList<TestItem>>


    @POST("test/")
    fun postTest(@Body testData: TestItem): Call<TestItem>


    //updating
    @PUT("/test/{id}") //pass id to modify
    fun updateTest(@Path("id")id: Int, @Body testData: TestItem): Call<TestItem>

    @DELETE("/test/{id}")
    fun deleteTest(@Path("id")id:Int): Call<Void>//void to override an existing post
}


















