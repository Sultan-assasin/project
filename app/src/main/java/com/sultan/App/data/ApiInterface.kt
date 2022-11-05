package com.sultan.App.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    fun getData() : Call<List<UserItem>>
}