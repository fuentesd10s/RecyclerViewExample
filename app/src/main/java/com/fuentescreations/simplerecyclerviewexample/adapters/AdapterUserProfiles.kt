package com.fuentescreations.simplerecyclerviewexample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fuentescreations.simplerecyclerviewexample.databinding.ItemUserProfileBinding
import com.fuentescreations.simplerecyclerviewexample.repository.models.UserProfile

class AdapterUserProfiles(private val userProfilesList:List<UserProfile>) : RecyclerView.Adapter<AdapterUserProfiles.UserProfilesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfilesViewHolder {
        val binding=ItemUserProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder= UserProfilesViewHolder(binding)
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