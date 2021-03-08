package com.fuentescreations.simplerecyclerviewexample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fuentescreations.simplerecyclerviewexample.databinding.ItemUserProfileBinding
import com.fuentescreations.simplerecyclerviewexample.data.models.UserProfile

class AdapterUserProfiles(private val userProfilesList:List<UserProfile>,private val onUserProfileClickListener: OnUserProfileClickListener) : RecyclerView.Adapter<AdapterUserProfiles.UserProfilesViewHolder>() {

    interface OnUserProfileClickListener{
        fun onShortUserProfileClickListener(userProfile: UserProfile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfilesViewHolder {
        val binding=ItemUserProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder= UserProfilesViewHolder(binding)

        binding.root.setOnClickListener {
            val position= holder.adapterPosition.takeIf { it!= DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener

            onUserProfileClickListener.onShortUserProfileClickListener(userProfilesList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: UserProfilesViewHolder, position: Int) { holder.bind(userProfilesList[position]) }


    override fun getItemCount(): Int = userProfilesList.size

    class UserProfilesViewHolder(private val binding: ItemUserProfileBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(userProfile:UserProfile) {
            binding.tvNickname.text=userProfile.nickname
            binding.tvContributionsCount.text="Contributions: ${userProfile.contributionsCount}"
        }
    }
}