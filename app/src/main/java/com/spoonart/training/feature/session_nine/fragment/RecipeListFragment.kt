package com.spoonart.training.feature.session_nine.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spoonart.training.R
import com.spoonart.training.feature.session_three.RecipeAdapter
import com.spoonart.training.model.data.Recipe

class RecipeListFragment : Fragment() {

    companion object {
        fun newInstance(items: List<Recipe>): RecipeListFragment {
            val f = RecipeListFragment()
            f.items = items
            return f
        }
    }

    private var items = listOf<Recipe>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container)
        recyclerView = view.findViewById(R.id.recyclerview)
        setAdapter()
        return view
    }

    fun setAdapter() {
        recyclerView.adapter = RecipeAdapter(items.toMutableList())
    }
}