package com.spoonart.training.util

class RecipeRequestUtil(var ingredients: String, var query: String, var page: Int) {

    fun nextPage(): RecipeRequestUtil {
        page++
        return this
    }

    fun previousPage(): RecipeRequestUtil {
        page--
        if (page <= 1) page = 1
        return this
    }

    fun resetPage() : RecipeRequestUtil{
        page = 1
        return this
    }

}

