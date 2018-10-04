package com.spoonart.training.feature.session_seven.network

import com.spoonart.training.model.response.RecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET("api/")
    fun getRecipesBy(
            @Query("i") ingredients: String?,
            @Query("q") food: String?,
            @Query("p") page: Int?
    ): Call<RecipeResponse>
}

