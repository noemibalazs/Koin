package com.example.koin.network_data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class TrailerList(
    @field:SerializedName("results") val trailerList: List<Trailer>
) : Parcelable {
    constructor(source: Parcel) : this(
        source.createTypedArrayList(Trailer.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(trailerList)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TrailerList> = object : Parcelable.Creator<TrailerList> {
            override fun createFromParcel(source: Parcel): TrailerList = TrailerList(source)
            override fun newArray(size: Int): Array<TrailerList?> = arrayOfNulls(size)
        }
    }
}