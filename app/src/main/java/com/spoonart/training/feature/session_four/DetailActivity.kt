package com.spoonart.training.feature.session_four

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.orm.SugarRecord
import com.spoonart.training.R
import com.spoonart.training.model.data.Recipe

class DetailActivity : AppCompatActivity() {

    var tvTitle: TextView? = null
    var tvIngredients: TextView? = null
    var ivThumbnail: ImageView? = null
    var recipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
        setData()
    }

    private fun initView() {
        tvTitle = findViewById(R.id.tv_title)
        tvIngredients = findViewById(R.id.tv_ingredients)
        ivThumbnail = findViewById(R.id.iv_thumbnail)
    }

    fun setData() {
        recipe = intent.getParcelableExtra<Recipe>("parcel")
        recipe?.let {
            tvTitle!!.text = it.title
            tvIngredients!!.text = it.ingredients
            val url = it.thumbnail
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
        }
        return false
    }

    fun showDialogConfirmation() {
        AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure want to delete Recipe?")
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

        if (SugarRecord.delete(recipe)) {
            finish()
        } else {
            Toast.makeText(this, "Gagal menghapus resep", Toast.LENGTH_SHORT).show()
        }
    }
}