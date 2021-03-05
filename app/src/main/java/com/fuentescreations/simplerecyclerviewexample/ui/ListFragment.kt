package com.fuentescreations.simplerecyclerviewexample.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fuentescreations.simplerecyclerviewexample.R
import com.fuentescreations.simplerecyclerviewexample.adapters.AdapterDogs
import com.fuentescreations.simplerecyclerviewexample.adapters.AdapterPhotos
import com.fuentescreations.simplerecyclerviewexample.adapters.AdapterUserProfiles
import com.fuentescreations.simplerecyclerviewexample.api.APICallBack
import com.fuentescreations.simplerecyclerviewexample.application.AppConstans
import com.fuentescreations.simplerecyclerviewexample.repository.models.Photos
import com.fuentescreations.simplerecyclerviewexample.databinding.FragmentListBinding
import com.fuentescreations.simplerecyclerviewexample.repository.models.UserProfile
import com.fuentescreations.simplerecyclerviewexample.viewmodels.DogsViewModel
import com.fuentescreations.simplerecyclerviewexample.viewmodels.PhotosViewModel
import com.fuentescreations.simplerecyclerviewexample.viewmodels.UserProfileViewModel

class ListFragment : Fragment(R.layout.fragment_list), AdapterPhotos.OnPhotosClickListener {

    private lateinit var binding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        arguments?.takeIf { it.containsKey(AppConstans.TAB_POSITION) }?.apply {

            when (getInt(AppConstans.TAB_POSITION)) {
                0 -> setupTabPhotos()
                1 -> setupTabDogs()
                2 -> setupUserProfiles()
            }
        }

    }

    private fun setupUserProfiles() {
        binding.btnRetry.setOnClickListener { setupUserProfiles() }

        binding.swipeRefreshLayout.setOnRefreshListener { setupUserProfiles() }

        showLoading()

        val userProfilesList = mutableListOf<UserProfile>()

        val adapterUserProfiles=AdapterUserProfiles(userProfilesList)
        binding.rv.adapter=adapterUserProfiles

        val viewModelUserProfiles=ViewModelProvider(this).get(UserProfileViewModel::class.java)

        val userProfilesObserver= Observer<List<UserProfile>> {
            userProfilesList.clear()
            userProfilesList.addAll(it)
            adapterUserProfiles.notifyDataSetChanged()
        }

        viewModelUserProfiles.getUserProfilesLiveData().observe(viewLifecycleOwner,userProfilesObserver)

        viewModelUserProfiles.getUserProfilesList(object : APICallBack{
            override fun onSuccess() {
                successLoading()
            }

            override fun onFailure(error: String) {
                errorLoading(error)
            }
        })
    }


    private fun setupTabDogs() {
        binding.btnRetry.setOnClickListener { setupTabDogs() }

        binding.swipeRefreshLayout.setOnRefreshListener { setupTabDogs() }

        showLoading()

        val listDogs = mutableListOf<String>()

        val adapterDogs = AdapterDogs(listDogs)
        binding.rv.adapter = adapterDogs

        val viewModelDogs = ViewModelProvider(this).get(DogsViewModel::class.java)

        val dogsObserver = Observer<List<String>> {
            listDogs.clear()
            listDogs.addAll(it)
            adapterDogs.notifyDataSetChanged()
        }

        viewModelDogs.getListDogsLiveData().observe(viewLifecycleOwner, dogsObserver)

        viewModelDogs.getListDogs(object : APICallBack {
            override fun onSuccess() {
                successLoading()
            }

            override fun onFailure(error: String) {
                errorLoading(error)
            }
        })
    }

    private fun setupTabPhotos() {
        binding.btnRetry.setOnClickListener { setupTabPhotos() }

        binding.swipeRefreshLayout.setOnRefreshListener { setupTabPhotos() }

        showLoading()

        val listPhotos = mutableListOf<Photos>()

        val adapterPhotos = AdapterPhotos(listPhotos, this@ListFragment)
        binding.rv.adapter = adapterPhotos

        val viewModelPhotos = ViewModelProvider(this).get(PhotosViewModel::class.java)

        val photosObserver = Observer<List<Photos>> {
            listPhotos.clear()
            listPhotos.addAll(it)
            adapterPhotos.notifyDataSetChanged()
        }

        viewModelPhotos.getListPhotosLiveData().observe(viewLifecycleOwner, photosObserver)

        viewModelPhotos.getListPhotos(object : APICallBack {
            override fun onSuccess() {
                successLoading()
            }

            override fun onFailure(error: String) {
                errorLoading(error)
            }
        })
    }

    private fun showLoading() {
        binding.rv.visibility = View.GONE
        binding.lyNoInternet.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = true
    }

    private fun successLoading() {
        binding.rv.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun errorLoading(error: String) {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.lyNoInternet.visibility = View.VISIBLE
        Log.d("Error", "onFailure: $error")
    }

    override fun onPhotosShortClickListener(modelPhotos: Photos) {
        Toast.makeText(requireContext(), modelPhotos.toString(), Toast.LENGTH_SHORT).show()
    }
}