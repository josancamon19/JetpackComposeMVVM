package com.josancamon19.composemvvmretrofit.network

import com.josancamon19.composemvvmretrofit.data.User
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .build()

interface UsersService {
    @GET("users")
    suspend fun getUsers(): List<User>
}

object UsersAPI {
    val retrofitService: UsersService by lazy { retrofit.create(UsersService::class.java) }
}