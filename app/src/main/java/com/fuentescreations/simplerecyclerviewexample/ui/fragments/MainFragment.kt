package com.fuentescreations.simplerecyclerviewexample.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fuentescreations.simplerecyclerviewexample.R
import com.fuentescreations.simplerecyclerviewexample.adapters.ViewPagerAdapter
import com.fuentescreations.simplerecyclerviewexample.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding:FragmentMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMainBinding.bind(view)

        viewPagerAdapter= ViewPagerAdapter(this)

        binding.viewPager2.adapter=viewPagerAdapter

        TabLayoutMediator(binding.tabLayout2,binding.viewPager2){tab,position->
            when(position){
                0->tab.text="User Profiles"
                1->tab.text="Dogs"
                2->tab.text="Photos"
            }
        }.attach()
    }
}