package com.spoonart.training.feature.session_nine

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import com.spoonart.training.R
import com.spoonart.training.feature.session_four.AddItemActivity
import com.spoonart.training.feature.session_nine.fragment.RecipeListFragment
import com.spoonart.training.feature.session_seven.network.ApiConfig
import com.spoonart.training.feature.session_seven.network.ApiRequest
import com.spoonart.training.feature.session_six.LoginActivity
import com.spoonart.training.model.data.Recipe
import com.spoonart.training.model.response.RecipeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainNavigationActivity : AppCompatActivity() {

    private var mTabLayout: TabLayout? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var mViewPager: ViewPager? = null
    private var toolbar: Toolbar? = null

    private lateinit var progressDialog: ProgressDialog

    private val service: ApiRequest by lazy {
        ApiConfig.getService(this)
    }

    var asyncTask: AsyncTask<Void, Void, MutableList<FragmentModel>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)
        configDialog()
        initView()
        requestDataByDefault()
    }

    private fun initView() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0f

        mDrawerLayout = findViewById(R.id.drawer_layout)
        mTabLayout = findViewById(R.id.tab_layout)
        mViewPager = findViewById(R.id.content)

        val navigationView = findViewById<NavigationView>(R.id.navigation)
        navigationView.setNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.mn_add -> {
                    startActivity(Intent(this, AddItemActivity::class.java))
                }

                R.id.mn_logout -> {
                    startActivity(Intent(this, LoginActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                }
            }

            item.isChecked = true
            mDrawerLayout!!.closeDrawer(Gravity.START)
            return@setNavigationItemSelectedListener true
        }

        setUpNavigationView()
    }

    private fun setUpNavigationView() {
        val actionBarDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {
            override fun onOptionsItemSelected(item: MenuItem?): Boolean {
                when (item!!.itemId) {
                    R.id.mn_refresh -> {
                        requestDataByDefault()
                    }

                    R.id.mn_logout -> {
                        LoginActivity.start(this@MainNavigationActivity)
                    }
                }
                mDrawerLayout!!.closeDrawers()
                return false
            }
        }
        mDrawerLayout!!.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }


    private fun configDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait...")
        progressDialog.setCancelable(false)
    }

    fun requestDataBy(ingredients: String?, food: String?, page: Int?) {
        progressDialog.show()
        val request = service.getRecipesBy(ingredients, food, page)
        request.enqueue(object : Callback<RecipeResponse> {

            override fun onFailure(call: Call<RecipeResponse>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<RecipeResponse>?, response: Response<RecipeResponse>?) {
                response?.let {
                    if (it.code() == 200) {
                        it.body()?.let { result ->
                            setContent(result.results)
                        }
                    } else {
                        Toast.makeText(this@MainNavigationActivity,
                                "An error occured code ${it.code()}", Toast.LENGTH_LONG)
                                .show()
                    }
                }
            }
        })
    }

    fun requestDataByDefault() {
        requestDataBy(null, null, null)
    }

    @SuppressLint("StaticFieldLeak")
    private fun setContent(recipes: MutableList<Recipe>) {
        asyncTask = object : AsyncTask<Void, Void, MutableList<FragmentModel>>() {

            //background process
            override fun doInBackground(vararg params: Void?): MutableList<FragmentModel> {
                val ascending = recipes.sortedBy { it.title }
                val descending = recipes.sortedByDescending { it.title }
                val fm1 = FragmentModel("Ascending", RecipeListFragment.newInstance(ascending))
                val fm2 = FragmentModel("Descending", RecipeListFragment.newInstance(descending))
                return mutableListOf(fm1, fm2)
            }

            //finish background process, return to ui/main thread
            override fun onPostExecute(result: MutableList<FragmentModel>?) {
                super.onPostExecute(result)
                result?.let {
                    val adapter = TabPagerAdapter.newInstance(supportFragmentManager, it)
                    mViewPager!!.adapter = adapter
                    mTabLayout!!.setupWithViewPager(mViewPager)
                }

                progressDialog.dismiss()

            }
        }

        asyncTask!!.execute()
    }
}