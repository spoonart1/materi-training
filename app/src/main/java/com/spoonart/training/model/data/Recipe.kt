package com.spoonart.training.model.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.orm.dsl.Table

//TODO : CREATE THIS CLASS AS PARCELABLE

@Table
class Recipe() : Parcelable {
    var title: String = ""
    var thumbnail: String = ""
    var ingredients: String = ""

    constructor(title: String, thumbnail: String, ingredients: String) : this() {
        this.title = title
        this.thumbnail = thumbnail
        this.ingredients = ingredients
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int){
        dest.writeString(this.title)
        dest.writeString(this.thumbnail)
        dest.writeString(this.ingredients)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Recipe> = object : Parcelable.Creator<Recipe> {
            override fun createFromParcel(source: Parcel): Recipe = Recipe(source)
            override fun newArray(size: Int): Array<Recipe?> = arrayOfNulls(size)
        }
    }
}