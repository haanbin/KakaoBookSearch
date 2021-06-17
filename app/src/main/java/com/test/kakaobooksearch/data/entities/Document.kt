package com.test.kakaobooksearch.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test.kakaobooksearch.data.local.dto.DocumentDto

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
            authorsSplit = it.authors.joinToString("|"),
            contents = it.contents,
            datetime = it.datetime,
            isbn = it.isbn,
            isLike = false,
            price = it.price,
            publisher = it.publisher,
            salePrice = it.salePrice,
            status = it.status,
            thumbnail = it.thumbnail,
            title = it.title,
            translatorSplit = it.translators.joinToString("|"),
            url = it.url
        )
    }
}
