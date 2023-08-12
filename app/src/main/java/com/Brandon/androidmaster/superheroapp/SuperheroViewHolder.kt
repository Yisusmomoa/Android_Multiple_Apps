package com.Brandon.androidmaster.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.Brandon.androidmaster.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //implementar viewbinding en un rv o en un viewholder
    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(superheroItemResponse: SuperheroItemResponse, onSuperheroSelected: (String) -> Unit) {
        binding.tvSuperheroName.text = superheroItemResponse.superheroName
        Picasso.get().load(superheroItemResponse.image.url).into(binding.ivSuperhero)
        //binding.root es toda la vista o card en este caso
        binding.root.setOnClickListener {
            onSuperheroSelected(superheroItemResponse.superheroId)
        }
    }
}