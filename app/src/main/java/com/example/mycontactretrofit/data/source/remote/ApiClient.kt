package com.example.mycontactretrofit.data.source.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.mycontactretrofit.app.App
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(ChuckerInterceptor.Builder(App.instance).build())
        .build()

     val retrofit = Retrofit.Builder()
        .baseUrl("https://ee4b-84-54-71-65.eu.ngrok.io")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}