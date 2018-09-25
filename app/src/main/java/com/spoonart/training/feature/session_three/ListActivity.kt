package com.spoonart.training.feature.session_three

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.spoonart.training.R
import com.spoonart.training.feature.session_five.DetailActivity
import com.spoonart.training.feature.session_four.AddItemActivity
import com.spoonart.training.model.data.Recipe
import com.spoonart.training.util.DataUtil

class ListActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    lateinit var adapter: RecipeAdapter

    companion object {
        fun start(from: Context) {
            from.startActivity(
                    Intent(from, ListActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }
    }

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
        adapter = RecipeAdapter(items)
        adapter.listener = object : RecipeAdapter.RecipeClickListener {
            override fun onClick(itemView: View, data: Recipe, position: Int) {
                DetailActivity.start(this@ListActivity, data)
            }
        }
        recyclerView!!.adapter = adapter
    }

}

