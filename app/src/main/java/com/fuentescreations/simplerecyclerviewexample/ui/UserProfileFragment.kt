package com.fuentescreations.simplerecyclerviewexample.ui

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.fuentescreations.simplerecyclerviewexample.R
import com.fuentescreations.simplerecyclerviewexample.adapters.AdapterOrganisations
import com.fuentescreations.simplerecyclerviewexample.application.BaseFragment
import com.fuentescreations.simplerecyclerviewexample.data.models.Organisations
import com.fuentescreations.simplerecyclerviewexample.data.models.UserProfile
import com.fuentescreations.simplerecyclerviewexample.databinding.FragmentUserProfileBinding

class UserProfileFragment : BaseFragment(R.layout.fragment_user_profile), AdapterOrganisations.OnOrganisationsClickListener {

    private lateinit var binding:FragmentUserProfileBinding
    private lateinit var userProfile:UserProfile
    private val args:UserProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentUserProfileBinding.bind(view)

        userProfile = args.userProfileArg

        setupView()
    }

    private fun setupView() {
        val adapterOrganisations=AdapterOrganisations(userProfile.organisations,this@UserProfileFragment)
        binding.rvOrganisations.adapter=adapterOrganisations

        binding.tvNickname.text=userProfile.nickname
        binding.tvContributionsCount.text="Contributions: ${userProfile.contributionsCount}"

        binding.github.setOnClickListener { openExternalLink(userProfile.githubProfile) }
        binding.twitter.setOnClickListener { openExternalLink(userProfile.twitterProfile) }

        if (userProfile.githubProfile.isNullOrEmpty())
            binding.github.visibility=View.GONE

        if (userProfile.twitterProfile.isNullOrEmpty())
            binding.twitter.visibility=View.GONE

        if (userProfile.organisations.isEmpty())
            binding.tvEmptyOrganisations.visibility=View.VISIBLE
    }

    override fun onShortOrganisationClickListener(organisation: Organisations) {
        openExternalLink(organisation.link)
    }

    private fun openExternalLink(url:String){
        AlertDialog.Builder(requireContext())
            .setTitle("Open external link?")
            .setPositiveButton("Open"){d,_->
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
                d.dismiss()
            }
            .setNeutralButton("Cancel"){d,_-> d.dismiss()}
            .show()
    }
}