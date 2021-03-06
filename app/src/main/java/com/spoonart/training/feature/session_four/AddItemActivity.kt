package com.spoonart.training.feature.session_four

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.spoonart.training.R
import com.spoonart.training.model.data.Recipe
import com.spoonart.training.util.DataUtil

class AddItemActivity : AppCompatActivity() {

    var etTitle: EditText? = null
    var etIngredients: EditText? = null
    var etThumbnail: EditText? = null
    var btnSave:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        setHomeAsUp()
        initView()
    }

    private fun setHomeAsUp(){
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return false
    }

    private fun initView() {
        etTitle = findViewById(R.id.et_title)
        etIngredients = findViewById(R.id.et_ingredients)
        etThumbnail = findViewById(R.id.et_thumbnail)
        btnSave = findViewById(R.id.btn_save)

        btnSave!!.setOnClickListener{
            if(isValid()){
                saveData()
                finish()
            }
        }
    }

    private fun saveData(){
        val recipe = Recipe()
        recipe.title = etTitle!!.text.toString()
        recipe.thumbnail = etThumbnail!!.text.toString()
        recipe.ingredients = etIngredients!!.text.toString()
        DataUtil.saveRecipe(recipe)
    }

    private fun isValid(): Boolean {
        listOf(etTitle, etIngredients, etThumbnail).forEach {
            if (TextUtils.isEmpty(it?.text)) {
                it?.error = "Tidak boleh kosong"
                return false
            }
        }
        return true
    }
}