package com.fuentescreations.simplerecyclerviewexample.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.fuentescreations.simplerecyclerviewexample.data.models.Organisations
import com.fuentescreations.simplerecyclerviewexample.databinding.ItemOrganisationsBinding
import com.squareup.picasso.Picasso

class AdapterOrganisations(private val organisationsList: List<Organisations>,private val onOrganisationsClickListener: OnOrganisationsClickListener) :
    RecyclerView.Adapter<AdapterOrganisations.OrganisationsViewHolder>() {

    interface OnOrganisationsClickListener {
        fun onShortOrganisationClickListener(organisation: Organisations)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganisationsViewHolder {
        val binding =
            ItemOrganisationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = OrganisationsViewHolder(binding)

        binding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION } ?: return@setOnClickListener

         onOrganisationsClickListener.onShortOrganisationClickListener(organisationsList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: OrganisationsViewHolder, position: Int) {
        holder.bind(organisationsList[position])
    }

    override fun getItemCount(): Int = organisationsList.size

    class OrganisationsViewHolder(private val binding: ItemOrganisationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(organisations: Organisations) {

            Glide.with(binding.root.context).load(organisations.avatarUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                }).into(binding.ivAvatar)

            binding.tvLogin.text = organisations.login
        }
    }
}