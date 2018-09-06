package com.spoonart.training.model.response

data class RecipeResponse(val title: String,
                  val thumbnail: String,
                  val ingredients: List<String>)