package com.adrenastudies.myapplication.api

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.Retrofit


class ApiConfig {

    companion object {

        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://reqres.in/"

        fun getClient() : Retrofit {

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit as Retrofit
        }
    }

}