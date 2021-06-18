package com.test.kakaobooksearch.vo

import android.os.Parcel
import android.os.Parcelable
import com.test.kakaobooksearch.dto.DocumentDto

data class DocumentVo(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: String,
    val publisher: String,
    val salePrice: String,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String,
    var isLike: Boolean = false
) : Parcelable {

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
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte()
    )

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
        parcel.writeByte(if (isLike) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DocumentVo> {
        override fun createFromParcel(parcel: Parcel): DocumentVo {
            return DocumentVo(parcel)
        }

        override fun newArray(size: Int): Array<DocumentVo?> {
            return arrayOfNulls(size)
        }
    }
}

fun List<DocumentDto>.toPresenter(): List<DocumentVo> {
    return map {
        DocumentVo(
            it.authors,
            it.contents,
            it.datetime,
            it.isbn,
            it.price,
            it.publisher,
            it.salePrice,
            it.status,
            it.thumbnail,
            it.title,
            it.translators,
            it.url,
            it.isLike
        )
    }
}
