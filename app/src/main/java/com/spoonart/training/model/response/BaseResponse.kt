package com.spoonart.training.model.response

import com.google.gson.annotations.SerializedName
import com.spoonart.training.model.data.Recipe

data class BaseResponse(@SerializedName("title") val title: String,
                        @SerializedName("version") val version: String,
                        @SerializedName("href") val link: String,
                        @SerializedName("results") val results: List<Recipe>)