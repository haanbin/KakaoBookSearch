package com.test.kakaobooksearch.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Document(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: String,
    val publisher: String,
    @SerializedName("sale_price")
    val salePrice: String,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String
) : Parcelable {
    var isLike: Boolean = false

    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList() ?: mutableListOf(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: mutableListOf(),
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(authors)
        parcel.writeString(contents)
        parcel.writeString(datetime)
        parcel.writeString(isbn)
        parcel.writeString(price)
        parcel.writeString(publisher)
        parcel.writeString(salePrice)
        parcel.writeString(status)
        parcel.writeString(thumbnail)
        parcel.writeString(title)
        parcel.writeStringList(translators)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Document> {
        override fun createFromParcel(parcel: Parcel): Document {
            return Document(parcel)
        }

        override fun newArray(size: Int): Array<Document?> {
            return arrayOfNulls(size)
        }
    }
}