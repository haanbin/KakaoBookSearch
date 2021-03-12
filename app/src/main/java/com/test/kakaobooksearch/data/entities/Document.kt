package com.test.kakaobooksearch.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test.kakaobooksearch.data.local.dto.DocumentDto

data class Document(
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: String,
    val publisher: String,
    @SerializedName("sale_price")
    val salePrice: String,
    val thumbnail: String,
    val title: String,
) : Parcelable {

    var isLike: Boolean = false

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(contents)
        parcel.writeString(datetime)
        parcel.writeString(isbn)
        parcel.writeString(price)
        parcel.writeString(publisher)
        parcel.writeString(salePrice)
        parcel.writeString(thumbnail)
        parcel.writeString(title)
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

fun List<Document>.toDocumentDto(metaId: Long): List<DocumentDto> {
    return map {
        DocumentDto(
            metaId = metaId,
            contents = it.contents,
            datetime = it.datetime,
            isbn = it.isbn,
            isLike = false,
            price = it.price,
            publisher = it.publisher,
            salePrice = it.salePrice,
            thumbnail = it.thumbnail,
            title = it.title
        )
    }
}