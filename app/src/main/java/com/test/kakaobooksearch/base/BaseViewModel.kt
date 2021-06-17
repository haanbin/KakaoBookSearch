package com.test.kakaobooksearch.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.kakaobooksearch.util.Event

abstract class BaseViewModel : ViewModel() {

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: LiveData<Event<String>>
        get() = _showToast

    private val _showToastStringRes = MutableLiveData<Event<Int>>()
    val showToastStringRes: LiveData<Event<Int>>
        get() = _showToastStringRes

    protected fun onShowToast(msg: String) {
        _showToast.value = Event(msg)
    }

    protected fun onShowToast(stringRes: Int) {
        _showToastStringRes.value = Event(stringRes)
    }
}
