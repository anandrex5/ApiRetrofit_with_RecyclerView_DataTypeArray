package com.company0ne.apiretrofit.API

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("posts/1/comments")
    fun getComment(): Call<JsonArray>


//    @POST("posts")
//    fun getPosts(): Call<JsonArray>
//
//
//    @GET("posts/1")
//    fun getData(): Call<JsonObject>

}


