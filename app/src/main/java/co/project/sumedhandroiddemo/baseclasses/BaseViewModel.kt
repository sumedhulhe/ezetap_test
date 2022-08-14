package co.project.sumedhandroiddemo.baseclasses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import co.project.rewards.baseclasses.BaseRepository

open class BaseViewModel(private val respository: BaseRepository): ViewModel() {

    init {
        if (!respository._errorResponse.hasObservers()) observeErrorResponse()
    }

    private val errorResponse = MutableLiveData<Error>()
    open fun observeErrorResponse(){
        respository._errorResponse.observeForever(Observer { response ->
            errorResponse.value = response
        })
    }

    fun getErrorResponse(): MutableLiveData<Error> {
        return errorResponse
    }
}