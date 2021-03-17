package com.test.kakaobooksearch.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.kakaobooksearch.util.Event
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: LiveData<Event<String>>
        get() = _showToast

    val compositeDisposable = CompositeDisposable()

    protected fun onShowToast(msg: String){
        _showToast.value = Event(msg)
    }
}