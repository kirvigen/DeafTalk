package com.kirvigen.deaftalk.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2


class FragmentAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    private val fragments: MutableList<Fragment> = ArrayList()
    fun addFragments(fragment: Fragment) {
        fragments.add(fragment)
    }

    fun getFragment(page: Int): Fragment {
        return fragments[page]
    }

    fun getActiveFragment(viewPager: ViewPager2): Fragment {
        return fragments[viewPager.currentItem]
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }
}