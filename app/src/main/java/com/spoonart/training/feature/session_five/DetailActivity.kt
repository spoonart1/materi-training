package com.spoonart.training.feature.session_five

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.spoonart.training.R
import com.spoonart.training.model.data.Recipe
import com.spoonart.training.util.DataUtil

class DetailActivity : AppCompatActivity() {

    var tvTitle: TextView? = null
    var tvIngredients: TextView? = null
    var ivThumbnail: ImageView? = null
    var recipe: Recipe? = null

    companion object {

        //a static method that will be called from previous activity / scene
        fun start(from: Context, parcel: Recipe) {
            val intent = Intent(from, DetailActivity::class.java)
            intent.putExtra("parcel", parcel)
            from.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setHomeAsUp()
        initView()
        setData()
    }

    private fun setHomeAsUp(){
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initView() {
        tvTitle = findViewById(R.id.tv_title)
        tvIngredients = findViewById(R.id.tv_ingredients)
        ivThumbnail = findViewById(R.id.iv_thumbnail)
    }

    fun setData() {
        recipe = intent.getParcelableExtra("parcel")
        println("RECIPE: ${recipe!!.title}")
        recipe?.let {
            tvTitle!!.text = it.title
            tvIngredients!!.text = it.ingredients
            val url = it.thumbnail
            setTitle(it.title)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.mn_delete -> {
                showDialogConfirmation()
            }
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return false
    }

    //showing a pop up dialog as confirmation, before deleting the current record
    fun showDialogConfirmation() {
        AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure want to delete this Recipe?")
                .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        delete(recipe)
                    }
                })
                .setNegativeButton("No", null)
                .show()
    }

    fun delete(recipe: Recipe?) {
        if (recipe == null)
            return

        //SugarRecord.delete(recipe)
        if (DataUtil.delete(recipe) > 0) {
            finish()
        } else {
            Toast.makeText(this, "Gagal menghapus resep", Toast.LENGTH_SHORT).show()
        }
    }
}