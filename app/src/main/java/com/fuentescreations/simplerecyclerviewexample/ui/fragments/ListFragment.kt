package com.fuentescreations.simplerecyclerviewexample.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fuentescreations.simplerecyclerviewexample.R
import com.fuentescreations.simplerecyclerviewexample.adapters.AdapterDogs
import com.fuentescreations.simplerecyclerviewexample.adapters.AdapterPhotos
import com.fuentescreations.simplerecyclerviewexample.adapters.AdapterUserProfiles
import com.fuentescreations.simplerecyclerviewexample.api.APICallBack
import com.fuentescreations.simplerecyclerviewexample.application.AppConstans
import com.fuentescreations.simplerecyclerviewexample.application.BaseFragment
import com.fuentescreations.simplerecyclerviewexample.application.ResultState
import com.fuentescreations.simplerecyclerviewexample.data.models.Photos
import com.fuentescreations.simplerecyclerviewexample.databinding.FragmentListBinding
import com.fuentescreations.simplerecyclerviewexample.data.models.UserProfile
import com.fuentescreations.simplerecyclerviewexample.data.remote.PhotosDataSource
import com.fuentescreations.simplerecyclerviewexample.domain.photos.PhotosRepoImpl
import com.fuentescreations.simplerecyclerviewexample.viewmodels.DogsViewModel
import com.fuentescreations.simplerecyclerviewexample.viewmodels.PhotosViewModel
import com.fuentescreations.simplerecyclerviewexample.viewmodels.PhotosViewModelFactory
import com.fuentescreations.simplerecyclerviewexample.viewmodels.UserProfileViewModel

class ListFragment : BaseFragment(R.layout.fragment_list), AdapterPhotos.OnPhotosClickListener,
    AdapterUserProfiles.OnUserProfileClickListener {

    private lateinit var binding: FragmentListBinding

    private val photosViewModel by viewModels<PhotosViewModel> {
        PhotosViewModelFactory(
            PhotosRepoImpl(
                PhotosDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        arguments?.takeIf { it.containsKey(AppConstans.TAB_POSITION) }?.apply {

            when (getInt(AppConstans.TAB_POSITION)) {
                0 -> setupUserProfiles()
                1 -> setupTabDogs()
                2 -> setupTabPhotos()
            }
        }
    }

    private fun setupUserProfiles() {
        binding.btnRetry.setOnClickListener { setupUserProfiles() }

        binding.swipeRefreshLayout.setOnRefreshListener { setupUserProfiles() }

        onShowLoading()

        val userProfilesList = mutableListOf<UserProfile>()

        val adapterUserProfiles = AdapterUserProfiles(userProfilesList, this@ListFragment)
        binding.rv.adapter = adapterUserProfiles

        val viewModelUserProfiles = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        val userProfilesObserver = Observer<List<UserProfile>> {
            userProfilesList.clear()
            userProfilesList.addAll(it)
            adapterUserProfiles.notifyDataSetChanged()
        }

        viewModelUserProfiles.getUserProfilesLiveData()
            .observe(viewLifecycleOwner, userProfilesObserver)

        viewModelUserProfiles.getUserProfilesList(object : APICallBack {
            override fun onSuccess() {
                onSuccessLoading()
            }

            override fun onFailure(error: String) {
                onErrorLoading(error)
            }
        })
    }


    private fun setupTabDogs() {
        binding.btnRetry.setOnClickListener { setupTabDogs() }

        binding.swipeRefreshLayout.setOnRefreshListener { setupTabDogs() }

        onShowLoading()

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
                onSuccessLoading()
            }

            override fun onFailure(error: String) {
                onErrorLoading(error)
            }
        })
    }

    private fun setupTabPhotos() {
        binding.btnRetry.setOnClickListener { setupTabPhotos() }

        binding.swipeRefreshLayout.setOnRefreshListener { setupTabPhotos() }

        val listPhotos = mutableListOf<Photos>()

        val adapterPhotos = AdapterPhotos(listPhotos, this@ListFragment)
        binding.rv.adapter = adapterPhotos

        photosViewModel.fetchLatestPhotos().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultState.Loading -> {
                    onShowLoading()
                }
                is ResultState.Success -> {
                    onSuccessLoading()

                    listPhotos.clear()
                    listPhotos.addAll(it.data)
                    adapterPhotos.notifyDataSetChanged()
                }
                is ResultState.Failure -> {
                    onErrorLoading(it.exception.toString())
                }
            }
        })

/*
        val viewModelPhotos = ViewModelProvider(this).get(PhotosViewModel::class.java)

        val photosObserver = Observer<List<Photos>> {
            listPhotos.clear()
            listPhotos.addAll(it)
            adapterPhotos.notifyDataSetChanged()
        }

        viewModelPhotos.getListPhotosLiveData().observe(viewLifecycleOwner, photosObserver)

        viewModelPhotos.getListPhotos(object : APICallBack {
            override fun onSuccess() {
                onSuccessLoading()
            }

            override fun onFailure(error: String) {
                onErrorLoading(error)
            }
        })
        */
    }

    private fun onShowLoading() {
        binding.rv.visibility = View.GONE
        binding.lyNoInternet.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = true
    }

    private fun onSuccessLoading() {
        binding.rv.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun onErrorLoading(error: String) {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.lyNoInternet.visibility = View.VISIBLE
        Log.d("Error", "onFailure: $error")
    }

    override fun onPhotosShortClickListener(modelPhotos: Photos) {
        mToast(modelPhotos.toString())
    }

    override fun onShortUserProfileClickListener(userProfile: UserProfile) {
        val action = MainFragmentDirections.actionMainFragmentToUserProfileFragment(userProfile)
        findNavController().navigate(action)
    }
}