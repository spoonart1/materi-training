package com.spoonart.training.feature.session_three

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.spoonart.training.R
import com.spoonart.training.feature.session_five.DetailActivity
import com.spoonart.training.feature.session_seven.network.ApiConfig
import com.spoonart.training.feature.session_seven.network.ApiRequest
import com.spoonart.training.model.data.Recipe
import com.spoonart.training.model.response.RecipeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: RecipeAdapter

    private val service: ApiRequest by lazy {
        ApiConfig.getService(this)
    }

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

        //load first time
        requestDataByDefault()
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

    fun requestDataBy(ingredients: String?, food: String?, page: Int?) {
        val request = service.getRecipesBy(ingredients, food, page)
        request.enqueue(object : Callback<RecipeResponse> {

            override fun onFailure(call: Call<RecipeResponse>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<RecipeResponse>?, response: Response<RecipeResponse>?) {
                response?.let {
                    if (it.code() == 200) {
                        it.body()?.let { result ->
                            setAdapter(result.results)
                        }
                    } else {
                        Toast.makeText(this@ListActivity,
                                "An error occured", Toast.LENGTH_LONG)
                                .show()
                    }
                }
            }
        })
    }

    fun requestDataByDefault() {
        requestDataBy(null, null, null)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater?.inflate(R.menu.menu_list, menu)
//
//        val searchView = menu?.findItem(R.id.mn_search)!!.actionView as SearchView
//        searchView.setOnQueryTextFocusChangeListener({ view, hasOFocus ->
//            if (hasOFocus) {
//                //adapter?.filter?.filter(searchView.query)
//            }
//        })
//
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item?.itemId) {
//            R.id.mn_add -> {
//                requestDataByDefault()
//            }
//        }
//
//        return false
//    }
}

