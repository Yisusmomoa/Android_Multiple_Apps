package com.Brandon.androidmaster.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Brandon.androidmaster.R

class SuperheroAdapter(
    var superheroeList: List<SuperheroItemResponse> = emptyList(),
    private val onSuperheroSelected: (String) -> Unit
) :
    RecyclerView.Adapter<SuperheroViewHolder>() {
    fun updateList(superheroeList: List<SuperheroItemResponse>) {
        this.superheroeList = superheroeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        return SuperheroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return superheroeList.size
    }

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.bind(superheroeList[position], onSuperheroSelected)
        /*holder.itemView.setOnClickListener {
            onSuperheroSelected(superheroeList[position].superheroId)
        }*/
    }
}