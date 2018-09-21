package com.spoonart.training.util

import com.orm.SugarRecord
import com.spoonart.training.model.data.Recipe

class DataUtil {

    companion object {

        fun getRecipeFromLocal() : MutableList<Recipe>{
            return SugarRecord.find(Recipe::class.java, null)
        }

        fun saveRecipe(recipe:Recipe){
            SugarRecord.save(recipe)
        }

        fun dummyData(): MutableList<Recipe> {
            return mutableListOf(
                    Recipe("Soup", "https://www.cookingclassy.com/wp-content/uploads/2014/10/vegetable-soup3-edit-srgb.-480x270.jpg", "carrot, potato, water, chicken"),
                    Recipe("Baked Omelet With Broccoli & Tomato", "http://img.recipepuppy.com/123889.jpg", "milk, cottage cheese, broccoli, cheddar cheese, basil, onion powder, eggs, garlic powder, roma tomato, salt"),
                    Recipe("Fried Rice", "https://www.stayathomemum.com.au/cache/555x700-0/wp-content/uploads/2015/06/Special-Fried-Rice.jpg", "rice, ketchup, salt, chicken"),
                    Recipe("Light Italian Feta Omelet", "http://img.recipepuppy.com/36027.jpg", "egg substitute, feta cheese, basil, roma tomato, salt"),
                    Recipe("Blue Cheese Omelet", "http://img.recipepuppy.com/177663.jpg", "blue cheese, butter, cinnamon, eggs, black pepper, salt, apple, walnut, water"),
                    Recipe("Vegan Omelet For One Recipe", "http://img.recipepuppy.com/328574.jpg", "salt, chipotle pepper, yeast, onion powder, potato starch, soy milk, tahini, turmeric"),
                    Recipe("Turkish Pasta with Bison Sauce", "http://img.recipepuppy.com/692185.jpg", "carrot, cheese, macaroni"),
                    Recipe("Roasted Carrots", "http://img.recipepuppy.com/641291.jpg", "carrot, italian dressing"),
                    Recipe("Whipped Carrot Salad", "http://img.recipepuppy.com/22570.jpg", "carrot, water"),
                    Recipe("Creamy Coleslaw", "http://img.recipepuppy.com/334187.jpg", "cabbage, carrot")
            )
        }

    }
}
