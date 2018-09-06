package com.spoonart.training.feature.session_seven

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class TabPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var models = mutableListOf<FragmentModel>()

    companion object {
        fun newInstance(fm: FragmentManager, models: MutableList<FragmentModel>): TabPagerAdapter {
            val pagerAdapter = TabPagerAdapter(fm)
            pagerAdapter.models = models
            return pagerAdapter
        }
    }

    override fun getItem(position: Int): Fragment {
        return models[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return models[position].title
    }

    override fun getCount(): Int {
        return models.size
    }

}

class FragmentModel(val title: String, val fragment: Fragment)