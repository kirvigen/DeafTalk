package com.kirvigen.deaftalk.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.kirvigen.deaftalk.R
import com.kirvigen.deaftalk.obj.Content
import com.kirvigen.deaftalk.obj.Course
import com.kirvigen.deaftalk.obj.Knowledge
import com.kirvigen.deaftalk.viewHolders.CourseViewHolder
import com.kirvigen.delegateadapterlibrary.DelegateManager
import kotlinx.android.synthetic.main.fragment_courses.view.*

class CourseFragment: Fragment() {

    var mainView:View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(R.layout.fragment_courses,container,false)
        val navController = activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }

        val data = loadData()

        CourseViewHolder.OnClickCourseListener = {
            val bundle = Bundle()
            bundle.putParcelable("data",it)
            navController?.navigate(R.id.action_courseFragment_to_contentFragment,bundle)
        }

        mainView?.recycler_courses?.layoutManager = GridLayoutManager(requireContext(),2)
        context?.let {
            mainView?.recycler_courses?.adapter = DelegateManager(data)
                .addHolder(CourseViewHolder(it))
                .build()
        }

        return mainView
    }

    fun loadData():MutableList<Any>{
        val data = mutableListOf<Any>()
        val contents = listOf<Content>(Knowledge("«A»","a"),Knowledge("«Б»","b"))

        for(i in 0..4)
            data.add(Course("Алфавит","ic_aa",
                "Весь русский алфавит в жестах","33 буквы",contents))
        return data
    }
}