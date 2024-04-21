package com.misw.vinilos_g24.brokers

import com.misw.vinilos_g24.models.Album
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://10.0.2.2:3000/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface RetrofitApiService {
    @GET("albums")
    fun getProperties():
            Call<List<Album>>

    @FormUrlEncoded
    @POST("collectors")
    fun postProperties(@Field("name") name: String,
                       @Field("telephone") telephone: String,
                       @Field("email") email: String):
            Call<String>
}

object RetrofitApi {
    val retrofitService : RetrofitApiService by lazy {
        retrofit.create(RetrofitApiService::class.java) }
}