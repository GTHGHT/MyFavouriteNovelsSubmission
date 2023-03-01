package com.example.myfavouritenovels.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NovelHeader(
    val title: String,
    val description: String,
    val imageLink: String
) : Parcelable {
    constructor(novelInfo: NovelInfo) : this(
        novelInfo.title,
        novelInfo.description,
        novelInfo.img_link
    )
}
