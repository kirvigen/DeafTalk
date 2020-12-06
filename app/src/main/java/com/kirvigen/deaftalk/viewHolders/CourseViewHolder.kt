package com.kirvigen.deaftalk.viewHolders

import android.content.Context
import android.view.View
import com.kirvigen.deaftalk.R
import com.kirvigen.deaftalk.obj.Course
import com.kirvigen.deaftalk.utils.Utils
import com.kirvigen.delegateadapterlibrary.DelegateHolder
import kotlinx.android.synthetic.main.item_course.*

class CourseViewHolder:DelegateHolder {
    constructor(context: Context):super(context)
    constructor(view: View):super(view)

    init {
        layoutId = R.layout.item_course
        classObject = Course::class.java
    }

    override fun bind(item: Any?) {
        item?.let {
            val course = it as Course
            header.text = course.header
            description.text = course.description
            counting.text = course.counting
            container.setOnClickListener { OnClickCourseListener?.let { it(course) } }
            icon_course.setImageResource(Utils.getResourseId(header.context,course.image))
        }
    }

    companion object{
        var OnClickCourseListener:((Course)->Unit)? = null
    }
}