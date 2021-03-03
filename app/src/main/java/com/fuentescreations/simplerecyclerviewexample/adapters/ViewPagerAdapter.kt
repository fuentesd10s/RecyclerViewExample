package com.fuentescreations.simplerecyclerviewexample.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fuentescreations.simplerecyclerviewexample.application.AppConstans
import com.fuentescreations.simplerecyclerviewexample.ui.ListFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val mFragment=ListFragment()

        mFragment.arguments= Bundle().apply {
            putInt(AppConstans.TAB_POSITION,position)
        }

        return mFragment
    }
}