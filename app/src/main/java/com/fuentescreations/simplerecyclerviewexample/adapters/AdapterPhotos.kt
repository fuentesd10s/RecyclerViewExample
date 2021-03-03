package com.fuentescreations.simplerecyclerviewexample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fuentescreations.simplerecyclerviewexample.repository.models.Photos
import com.fuentescreations.simplerecyclerviewexample.databinding.ItemPhotosBinding
import com.squareup.picasso.Picasso

class AdapterPhotos(private val listPhotos: List<Photos>, private val onItemClickListener: OnPhotosClickListener) :RecyclerView.Adapter<AdapterPhotos.PhotosViewHolder>(){

    interface OnPhotosClickListener{
        fun onPhotosShortClickListener(modelPhotos: Photos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding=ItemPhotosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder=PhotosViewHolder(binding)

        binding.root.setOnClickListener {
            val position=holder.adapterPosition.takeIf { it!= DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener

            onItemClickListener.onPhotosShortClickListener(listPhotos[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) { holder.bind(listPhotos[position]) }

    override fun getItemCount(): Int = listPhotos.size

    class PhotosViewHolder(private val binding: ItemPhotosBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(modelPhotos: Photos){

            binding.tvTitle.text=modelPhotos.title

//            Glide.with(binding.root.context).load(modelPhotos.thumbnailUrl).into(binding.ivThumbnail)
            Picasso.get().load(modelPhotos.thumbnailUrl).into(binding.ivThumbnail)
        }
    }
}