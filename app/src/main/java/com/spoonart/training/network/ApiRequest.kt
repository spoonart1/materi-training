package com.spoonart.training.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET
    fun getRecipesBy(
            @Query("i") inggrigients: String,
            @Query("q") food: String,
            @Query("p") page: Int = 1
    )
}