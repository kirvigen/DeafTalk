package com.kirvigen.deaftalk.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kirvigen.deaftalk.R
import com.kirvigen.deaftalk.obj.Knowledge
import com.kirvigen.deaftalk.utils.Utils
import kotlinx.android.synthetic.main.item_simple_element.view.*

class ItemViewerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_item_viewer,container,false)
        arguments?.getParcelable<Knowledge>("data")?.let { initViews(it,view) }
        return view
    }

    private fun initViews(knowledge: Knowledge,view: View) {
        view.image.setImageResource(Utils.getResourseId(requireContext(),knowledge.image,"raw"))
        view.name.text = knowledge.name
    }

}