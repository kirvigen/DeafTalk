package com.kirvigen.deaftalk.viewHolders

import android.content.Context
import android.view.View
import com.kirvigen.deaftalk.R
import com.kirvigen.deaftalk.obj.Course
import com.kirvigen.deaftalk.obj.Knowledge
import com.kirvigen.deaftalk.utils.Utils
import com.kirvigen.delegateadapterlibrary.DelegateHolder
import kotlinx.android.synthetic.main.fragment_item_viewer.*

import kotlinx.android.synthetic.main.item_simple_element.container
import kotlinx.android.synthetic.main.item_simple_element.image
import kotlinx.android.synthetic.main.item_simple_element.name


class KnowledgeViewHolder:DelegateHolder  {
    constructor(context: Context):super(context)
    constructor(view: View):super(view)

    init {
        layoutId = R.layout.item_simple_element
        classObject = Knowledge::class.java
    }

    override fun bind(item: Any?) {
        item?.let {
            val knowledge = it as Knowledge
            name.text = knowledge.name
            image.setImageResource(Utils.getResourseId(image.context,knowledge.image,"raw"))
            container.setOnClickListener { OnClickKnowledgeListener?.let { it(knowledge) } }
        }
    }

    companion object{
        var OnClickKnowledgeListener:((Knowledge)->Unit)? = null
    }
}