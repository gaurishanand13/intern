package com.example.internapp

import com.example.internapp.DataClass.Article
import com.example.internapp.DataClass.topHeadLines
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


object RetrofitClient{
    val retrofit = Retrofit.Builder()
        .baseUrl("http://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var searchView: com.miguelcatalan.materialsearchview.MaterialSearchView? = null


    val apiKey = "8460bc98d1a344cebfee030f4bbf7f89"
    val retrofitService = retrofit.create(myInterface::class.java)
    var retrofitSearch : String = ""

    var adapter : myAdapter? = null
    var articles : ArrayList<Article>? = ArrayList<Article>()
}


object SESSION_ID_DETAILS{
    var sessionID : String?=null
}

public interface myInterface{

    @GET("top-headlines?country=us&apiKey=8460bc98d1a344cebfee030f4bbf7f89")
    fun getTopNews(): Call<topHeadLines>

    @GET("everything?apiKey=8460bc98d1a344cebfee030f4bbf7f89")
    fun getSearch(@Query("q") q :String) : Call<topHeadLines>

}