package com.kirvigen.deaftalk.utils

import android.content.Context
import com.kirvigen.deaftalk.obj.Course

class Utils {
    companion object{
        @Throws(RuntimeException::class)
        fun getResourseId(context: Context, pVariableName: String?, type:String = "drawable"): Int {
            return try {
                context.resources.getIdentifier(pVariableName, type, context.packageName)
            } catch (e: Exception) {

                throw RuntimeException("Error getting Resource ID.", e)
            }
        }
    }
}
