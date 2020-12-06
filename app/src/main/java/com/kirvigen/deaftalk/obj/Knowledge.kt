package com.kirvigen.deaftalk.obj

import android.os.Parcel
import android.os.Parcelable

data class Knowledge(val name:String,val image:String ):Content(),Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Knowledge> {
        override fun createFromParcel(parcel: Parcel): Knowledge {
            return Knowledge(parcel)
        }

        override fun newArray(size: Int): Array<Knowledge?> {
            return arrayOfNulls(size)
        }
    }
}