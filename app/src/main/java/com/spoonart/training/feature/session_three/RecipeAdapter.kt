package com.spoonart.training.feature.session_three

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.spoonart.training.R
import com.spoonart.training.model.data.Recipe



class RecipeAdapter(var items: MutableList<Recipe>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    val originalData = items
    var listener: RecipeClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_recipe, parent, false)
        return VH(view, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as VH
        val data = items[position]
        vh.bind(data, position)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filtered = mutableListOf<Recipe>()
                val results = FilterResults()
                if (!constraint.isNullOrBlank()) {
                    items.forEach {
                        if (constraint!!.contains(it.toString())) {
                            filtered.add(it)
                        }
                    }
                    results.values = filtered

                } else {
                    results.values = originalData
                }

                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val data = results!!.values as MutableList<Recipe>
                items = data
                notifyDataSetChanged()
            }

        }
    }


    internal class VH(itemView: View, val listener: RecipeClickListener?) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tv_title)
        }

        fun bind(data: Recipe, position: Int) {
            tvTitle.text = data.title
            itemView.setOnClickListener {
                listener?.onClick(itemView, data, position)
            }
        }
    }

    interface RecipeClickListener {
        fun onClick(itemView: View, data: Recipe, position: Int)
    }
}

