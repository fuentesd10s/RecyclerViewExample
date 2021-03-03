package com.fuentescreations.simplerecyclerviewexample.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fuentescreations.simplerecyclerviewexample.R
import com.fuentescreations.simplerecyclerviewexample.adapters.AdapterPhotos
import com.fuentescreations.simplerecyclerviewexample.api.APICallBack
import com.fuentescreations.simplerecyclerviewexample.application.AppConstans
import com.fuentescreations.simplerecyclerviewexample.repository.models.Photos
import com.fuentescreations.simplerecyclerviewexample.databinding.FragmentListBinding
import com.fuentescreations.simplerecyclerviewexample.viewmodels.PhotosViewModel

class ListFragment : Fragment(R.layout.fragment_list), AdapterPhotos.OnPhotosClickListener {

    private lateinit var binding: FragmentListBinding

    private lateinit var viewModelPhotos:PhotosViewModel
    private val listPhotos = mutableListOf<Photos>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        binding.btnRetry.setOnClickListener {
            getListPhotos()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            getListPhotos()
        }

        arguments?.takeIf { it.containsKey(AppConstans.TAB_POSITION) }?.apply {

            when (getInt(AppConstans.TAB_POSITION)) {
                0 -> setupTabPhotos()
                1 -> setupTabImages()
            }
        }

    }

    private fun setupTabImages() {

    }

    private fun setupTabPhotos() {

        val adapterPhotos = AdapterPhotos(listPhotos, this@ListFragment)
        binding.rv.adapter = adapterPhotos

        viewModelPhotos = ViewModelProvider(this).get(PhotosViewModel::class.java)

        val photos = Observer<List<Photos>> {
            listPhotos.clear()
            listPhotos.addAll(it)
            adapterPhotos.notifyDataSetChanged()
        }

        viewModelPhotos.getListPhotosLiveData().observe(viewLifecycleOwner, photos)

        getListPhotos()
    }

    private fun getListPhotos(){
        binding.rv.visibility=View.GONE
        binding.lyNoInternet.visibility=View.GONE
        binding.swipeRefreshLayout.isRefreshing=true

        viewModelPhotos.getListPhotos(object : APICallBack {
            override fun onSuccess() {
                binding.rv.visibility=View.VISIBLE
                binding.swipeRefreshLayout.isRefreshing=false
            }

            override fun onFailure(error: String) {
                binding.swipeRefreshLayout.isRefreshing=false
                binding.lyNoInternet.visibility=View.VISIBLE

                Log.d("Error", "onFailure: $error")
            }

        })
    }

    override fun onPhotosShortClickListener(modelPhotos: Photos) {
        Toast.makeText(requireContext(), modelPhotos.toString(), Toast.LENGTH_SHORT).show()
    }
}