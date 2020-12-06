package com.kirvigen.deaftalk.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kirvigen.deaftalk.R
import com.kirvigen.deaftalk.obj.Content
import com.kirvigen.deaftalk.obj.Course
import com.kirvigen.deaftalk.obj.Knowledge
import com.kirvigen.deaftalk.viewHolders.KnowledgeViewHolder
import com.kirvigen.delegateadapterlibrary.DelegateManager
import kotlinx.android.synthetic.main.fragment_content.view.*
import java.util.ArrayList

class ContentFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_content,container,false)
        arguments?.getParcelable<Course>("data")?.let { initViews(it,view) }
        return view
    }

    private fun initViews(course: Course,view:View) {
        val navController =  Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        view.back?.setOnClickListener { navController.popBackStack() }
        view.header.text = course.header

        val adapterManager = DelegateManager(course.items.toMutableList())
        when(course.items[0]){
            is Knowledge ->{
                KnowledgeViewHolder.OnClickKnowledgeListener = { knowledge->
                    val b = Bundle()
                    b.putString("header",course.header)
                    b.putParcelableArrayList("data",course.items.toMutableList() as ArrayList<Knowledge>)
                    var pos = course.items.indexOf(knowledge)
                    b.putInt("position",pos)
                    navController.navigate(R.id.action_contentFragment_to_viewerFragment,b)
                }
                adapterManager.addHolder(KnowledgeViewHolder(requireContext()))
                view.recycler_content.layoutManager = GridLayoutManager(requireContext(),2)
            }
        }
        view.recycler_content.adapter = adapterManager.build()
    }

}