package com.kirvigen.deaftalk.viewHolders

import android.content.Context
import android.view.View
import com.kirvigen.deaftalk.R
import com.kirvigen.deaftalk.obj.Knowledge
import com.kirvigen.deaftalk.obj.UnderCourse
import com.kirvigen.deaftalk.utils.Utils
import com.kirvigen.delegateadapterlibrary.DelegateHolder
import kotlinx.android.synthetic.main.item_simple_element.*

class UnderCourseHolder : DelegateHolder {
    constructor(context: Context):super(context)
    constructor(view: View):super(view)

    init {
        layoutId = R.layout.item_under_course
        classObject = UnderCourse::class.java
    }

    override fun bind(item: Any?) {
        item?.let {
            val underCourse = it as UnderCourse
            name.text = underCourse.name
            container.setOnClickListener { OnClickUnderCourse?.let { it(underCourse) } }
        }
    }

    companion object{
        var OnClickUnderCourse:((UnderCourse)->Unit)? = null
    }
}