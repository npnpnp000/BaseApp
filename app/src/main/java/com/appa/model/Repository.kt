package com.appa.model

import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import com.adviserall22spdaslld.model.response.MainResponses
import com.adviserall22spdaslld.model.response.RequestWebService
import com.appa.model.entities.Message
import com.appb.model.database.database.MessageDao


class Repository(
    val messageDou: MessageDao,
    val webService: RequestWebService,
) {


    @Throws(Exception::class)
    suspend fun getRequest() :MainResponses
            = webService.getRequest()
    @WorkerThread
    suspend fun insertMessageToRoom(message: Message) {
        Log.e("insert - Repository","start: "+ message.toString())
        messageDou.insertMessage(message)
        Log.e("insert - Repository","after")
    }

    fun allMessages() = messageDou.getAllMessagesList()

}