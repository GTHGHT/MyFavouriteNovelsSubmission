package com.example.myfavouritenovels.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NovelInfo(
    val artists: List<String>?,
    val authors: List<String>,
    val description: String,
    val genre: List<String>,
    val genre_id: List<String>,
    val img_link: String,
    val language: String,
    val rating: String,
    val sid: String,
    val tag: List<String>,
    val title: String,
    val type: String,
    val year: String
):Parcelable