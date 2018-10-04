package com.spoonart.training.feature.session_eight

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.spoonart.training.R
import com.spoonart.training.feature.session_five.DetailActivity
import com.spoonart.training.feature.session_seven.network.ApiConfig
import com.spoonart.training.feature.session_seven.network.ApiRequest
import com.spoonart.training.feature.session_three.RecipeAdapter
import com.spoonart.training.model.data.Recipe
import com.spoonart.training.model.response.RecipeResponse
import com.spoonart.training.util.RecipeRequestUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity2 : AppCompatActivity() {

    private var contentSearch: LinearLayout? = null
    private var etIngridients: TextInputEditText? = null
    private var etQuery: TextInputEditText? = null
    private var etPage: TextInputEditText? = null
    private var btnSearch: Button? = null

    private var recyclerView: RecyclerView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    private var recipeUtil: RecipeRequestUtil? = null
    private lateinit var adapter: RecipeAdapter

    private val service: ApiRequest by lazy {
        ApiConfig.getService(this)
    }

    companion object {
        fun start(from: Context) {
            from.startActivity(
                    Intent(from, ListActivity2::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list2)
        initView()
    }

    fun initView() {

        contentSearch = findViewById(R.id.content_search)
        etIngridients = findViewById(R.id.et_ingredients)
        etQuery = findViewById(R.id.et_query)
        etPage = findViewById(R.id.et_page)
        btnSearch = findViewById(R.id.btn_search)

        swipeRefreshLayout = findViewById(R.id.swipe_refresh)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView?.let {
            it.layoutManager = LinearLayoutManager(this)
        }

        setCustomSearchListener()
        setOnRefreshListener()
        setOnScrollListener()

        //load first time
        requestDataByDefault()
    }

    private fun setCustomSearchListener() {
        btnSearch!!.setOnClickListener {
            recipeUtil = RecipeRequestUtil(
                    etIngridients!!.text.toString(),
                    etQuery!!.text.toString(),
                    etPage!!.text.toString().toInt())

            toggleCustomSearch()
            requestDataBy(recipeUtil!!.ingredients, recipeUtil!!.query, recipeUtil!!.page)
        }
    }

    private fun setOnRefreshListener() {
        swipeRefreshLayout!!.setOnRefreshListener {
            recipeUtil?.let {
                requestDataBy(recipeUtil!!.ingredients, recipeUtil!!.query, recipeUtil!!.page)
            }
        }
    }


    //listen when recylerview scrolled at the very bottom list
    fun setOnScrollListener() {
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (!recyclerView!!.canScrollVertically(1)) {
                    onScrolledToBottom();
                }
            }
        })
    }

    private fun onScrolledToBottom() {
        recipeUtil?.let {
            requestDataBy(recipeUtil!!.ingredients, recipeUtil!!.query, recipeUtil!!.nextPage().page)
        }
    }

    fun setAdapter(items: MutableList<Recipe>) {
        adapter = RecipeAdapter(items)
        adapter.listener = object : RecipeAdapter.RecipeClickListener {
            override fun onClick(itemView: View, data: Recipe, position: Int) {
                DetailActivity.start(this@ListActivity2, data)
            }
        }
        recyclerView!!.adapter = adapter
    }

    fun requestDataBy(ingredients: String?, food: String?, page: Int?) {
        setOnRefreshLayout(true)
        val request = service.getRecipesBy(ingredients, food, page)
        request.enqueue(object : Callback<RecipeResponse> {

            override fun onFailure(call: Call<RecipeResponse>?, t: Throwable?) {
                t?.printStackTrace()
                setOnRefreshLayout(false)
            }

            override fun onResponse(call: Call<RecipeResponse>?, response: Response<RecipeResponse>?) {
                response?.let {
                    if (it.code() == 200) {
                        it.body()?.let { result ->
                            setAdapter(result.results)
                        }
                    } else {
                        Toast.makeText(this@ListActivity2, "An error occured", Toast.LENGTH_LONG).show()
                    }
                    setOnRefreshLayout(false)
                }
            }
        })
    }

    fun requestDataByDefault() {
        requestDataBy(null, null, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.menu_list, menu)

        val searchView = menu?.findItem(R.id.mn_search)!!.actionView as SearchView
        searchView.setOnQueryTextFocusChangeListener({ view, hasOFocus ->
            if (hasOFocus) {
                //adapter?.filter?.filter(searchView.query)
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.mn_add -> {
                toggleCustomSearch()
            }
        }

        return false
    }

    private fun toggleCustomSearch(){
        if (contentSearch?.visibility == View.VISIBLE) {
            contentSearch?.visibility = View.GONE
        } else {
            contentSearch?.visibility = View.VISIBLE
        }
    }

    private fun setOnRefreshLayout(enabled:Boolean){
        swipeRefreshLayout?.post {
            swipeRefreshLayout?.isRefreshing = enabled
        }
    }

}

