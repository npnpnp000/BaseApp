package com.appa.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.appa.model.Repository
import com.appa.model.entities.Message
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository) : ViewModel(){

    var allMessagesLiveData : MutableLiveData<List<Message>?> =  MutableLiveData<List<Message>?>()

     fun allMessages()= viewModelScope.launch{
    repository.allMessages().collect {
        Log.e("allMessages2 - VM",it.toString())
        allMessagesLiveData.value = it
    }
}
    fun insertMessageToRoom(message: Message) = viewModelScope.launch{
        Log.e("insert - VM","start: "+ message.toString())
        repository.insertMessageToRoom(message)
        Log.e("insert - VM","after")
//        allMessages()
    }
}


