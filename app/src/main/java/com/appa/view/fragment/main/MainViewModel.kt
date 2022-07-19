package com.appa.viewModel

import androidx.lifecycle.*
import com.appa.model.Repository
import com.appa.model.entities.Message
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository) : ViewModel(){

    var allMessagesLiveData : MutableLiveData<List<Message>?> =  MutableLiveData<List<Message>?>()

    fun allMessages()= viewModelScope.launch{
    repository.allMessages().collect {
        allMessagesLiveData.value = it
    }
}
    fun insertMessageToRoom(message: Message) = viewModelScope.launch{
        repository.insertMessageToRoom(message)
        allMessages()  // Call allMessages to test the insert method
    }
}


