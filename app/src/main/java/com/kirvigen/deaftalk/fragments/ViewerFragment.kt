package com.kirvigen.deaftalk.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.kirvigen.deaftalk.R
import com.kirvigen.deaftalk.obj.Knowledge
import com.kirvigen.deaftalk.utils.FragmentAdapter
import kotlinx.android.synthetic.main.fragment_content.view.*
import kotlinx.android.synthetic.main.fragment_viewer.*
import kotlinx.android.synthetic.main.fragment_viewer.view.*
import kotlinx.android.synthetic.main.fragment_viewer.view.back
import kotlinx.android.synthetic.main.fragment_viewer.view.header
import java.util.ArrayList

class ViewerFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_viewer, container, false)
        arguments?.getString("header")?.let { view.header.text = it }
        arguments?.getParcelableArrayList<Knowledge>("data")?.let { initViews(it,view) }
        arguments?.getInt("position")?.let { view.viewPager.setCurrentItem(it,false) }
        return view
    }

    private fun initViews(listData: ArrayList<Knowledge>, view: View) {
        val navController = activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }

        view.back?.setOnClickListener { navController?.popBackStack() }
        val adapter = FragmentAdapter(requireActivity())

        for (i in 0 until listData.size){
            val b = Bundle()
            b.putParcelable("data",listData[i])
            val itemViewerFragment = ItemViewerFragment()
            itemViewerFragment.arguments = b
            adapter.addFragments(itemViewerFragment)
        }

        view.viewPager.adapter = adapter
        view.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                view.count.text = "${position+1}/${listData.size}"
                if(position == listData.size-1)
                    view.next.visibility = View.GONE
                else
                    view.next.visibility = View.VISIBLE

                if(position == 0)
                    view.backPager.visibility = View.GONE
                else
                    view.backPager.visibility = View.VISIBLE

            }
        })

        view.backPager.setOnClickListener { view.viewPager.setCurrentItem(view.viewPager.currentItem-1,true) }
        view.next.setOnClickListener { view.viewPager.setCurrentItem(view.viewPager.currentItem+1,true) }
    }
}