package com.example.postrequests

import android.text.Editable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface{

    @GET("test/")
    fun getTest(): Call<ArrayList<TestItem>>


    @POST("test/")
    fun postTest(@Body testData: TestItem): Call<TestItem>

}
