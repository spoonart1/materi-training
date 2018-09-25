package com.spoonart.training.network

import com.spoonart.training.model.data.Recipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET
    fun getRecipesBy(
            @Query("i") ingredients: String,
            @Query("q") food: String,
            @Query("p") page: Int = 1
    ) : Call<Recipe>
}