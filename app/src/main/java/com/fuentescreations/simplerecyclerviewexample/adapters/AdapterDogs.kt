package com.fuentescreations.simplerecyclerviewexample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fuentescreations.simplerecyclerviewexample.data.models.Dogs
import com.fuentescreations.simplerecyclerviewexample.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class AdapterDogs(private val dogsImages: List<String>) : RecyclerView.Adapter<AdapterDogs.DogsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val binding=ItemDogBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder= DogsViewHolder(binding)

        return holder
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) { holder.bind(dogsImages[position]) }

    override fun getItemCount(): Int = dogsImages.size

    class DogsViewHolder(private val binding:ItemDogBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(dogImageUrl: String){
            Glide.with(binding.root.context).load(dogImageUrl).into(binding.ivDog)
        }
    }
}