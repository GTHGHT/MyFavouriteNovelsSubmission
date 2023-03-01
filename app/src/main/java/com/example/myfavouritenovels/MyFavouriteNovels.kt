package com.example.myfavouritenovels

import com.google.android.material.color.DynamicColors

class MyFavouriteNovels: android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}