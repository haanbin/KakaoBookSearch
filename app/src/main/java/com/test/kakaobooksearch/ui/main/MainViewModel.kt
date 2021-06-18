package com.test.kakaobooksearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.kakaobooksearch.base.BaseViewModel
import com.test.kakaobooksearch.util.Event
import com.test.kakaobooksearch.vo.DocumentVo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _changeDocumentAction = MutableLiveData<Event<DocumentVo>>()
    val changeDocumentAction: LiveData<Event<DocumentVo>>
        get() = _changeDocumentAction

    fun onDocumentChange(document: DocumentVo) {
        _changeDocumentAction.value = Event(document)
    }
}
