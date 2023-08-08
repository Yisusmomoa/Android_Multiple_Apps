package com.Brandon.androidmaster.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Brandon.androidmaster.R

class CategoriesAdapter(private val categories: List<TaskCategory>) :
    RecyclerView.Adapter<CategoriesViewHolder>() {
    //crear una vista visual, y montar esa vista para que el método onBindViewHolder pueda pasarle la información que debe de pintar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        //le retorno el layout que es el item_task_category para que en el viewHolder lo pinte
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position])
    }

}