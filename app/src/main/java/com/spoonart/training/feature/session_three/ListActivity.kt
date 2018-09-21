package com.spoonart.training.feature.session_three

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.orm.SugarRecord
import com.spoonart.training.R
import com.spoonart.training.feature.session_four.AddItemActivity
import com.spoonart.training.model.data.Recipe
import com.spoonart.training.util.DataUtil

class ListActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var adapter: RecipeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initView()
    }

    fun initView() {
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData() {
        setAdapter(DataUtil.getRecipeFromLocal())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.menu_list, menu)

//        val searchView = menu?.findItem(R.id.mn_search)!!.actionView as SearchView
//        searchView.setOnQueryTextFocusChangeListener({ view, hasOFocus ->
//            if (hasOFocus) {
//                adapter?.filter?.filter(searchView.query)
//            }
//        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.mn_add -> {
                startActivity(
                        Intent(this, AddItemActivity::class.java)
                )
            }
        }

        return false
    }

    fun setAdapter(items: MutableList<Recipe>) {
        val adapter = RecipeAdapter(items)
        adapter.listener = object : RecipeAdapter.RecipeClickListener {
            override fun onClick(itemView: View, data: Recipe, position: Int) {

            }
        }
        recyclerView!!.adapter = adapter
    }

}