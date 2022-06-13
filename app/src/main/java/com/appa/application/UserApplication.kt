package com.appa.application

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.adviserall22spdaslld.model.response.RequestWebService
import com.appa.model.Repository
import com.appb.model.database.database.MessageRoomDatabase

/**
 * A application class where we can define the variable scope to use through out the application.
 */
class UserApplication() : Application() {

    private val database by lazy { MessageRoomDatabase.getDatabase(this@UserApplication) }
    private val webService by lazy { RequestWebService()}
    // A variable for repository.
    val repository by lazy { Repository(database.messageDou(), webService) }
}