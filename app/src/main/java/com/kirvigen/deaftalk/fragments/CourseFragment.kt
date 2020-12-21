package com.kirvigen.deaftalk.fragments

import android.os.Bundle
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
import org.json.JSONArray
import java.io.IOException
import java.lang.Exception
import java.nio.charset.Charset

class CourseFragment: Fragment() {

    var mainView:View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainView = inflater.inflate(R.layout.fragment_courses, container, false)
        val navController = activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }

        val data = loadData()

        CourseViewHolder.OnClickCourseListener = {
            val bundle = Bundle()
            bundle.putParcelable("data", it)
            navController?.navigate(R.id.action_courseFragment_to_contentFragment, bundle)
        }

        mainView?.recycler_courses?.layoutManager = GridLayoutManager(requireContext(), 2)
        context?.let {
            mainView?.recycler_courses?.adapter = DelegateManager(data)
                .addHolder(CourseViewHolder(it))
                .build()
        }

        return mainView
    }

    fun loadData():MutableList<Any>{
        val result = mutableListOf<Course>()
        try {
            val `is` = context?.assets?.open("data.json")
            val size = `is`?.available()
            val buffer = size?.let { ByteArray(it) }
            `is`?.read(buffer)
            `is`?.close()
            val json = buffer?.let { String(buffer, Charset.forName("UTF-8")) }
            val obj = JSONArray(json)
            for(i in 0 until obj.length()){
                val oc = obj.getJSONObject(i)
                val course = Course(oc.getString("header"),oc.getString("icon"),
                    oc.getString("description"),oc.getString("dop"),
                    parceItems(oc.getJSONArray("items")))
                result.add(course)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return result.toMutableList()
    }

    fun parceItems(items: JSONArray):MutableList<Knowledge>{
        val result = mutableListOf<Knowledge>()
        try{
            for(i in 0 until items.length()){
                val obj = items.getJSONObject(i)
                val item = Knowledge("«${obj.getString("name")}»",obj.getString("path"))
                result.add(item)
            }
        }catch (e:Exception){

        }
        return result
    }
}