package com.adrenastudies.myapplication.api.services

import com.adrenastudies.myapplication.model.User
import com.adrenastudies.myapplication.model.ListUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Users {

    @GET("/api/users")
    fun getAllUsers(): Call<ListUsers>

    @GET("/api/users/{id}")
    fun getUsers(@Path("id") id: String): Call<User>

}