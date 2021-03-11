package com.test.kakaobooksearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.kakaobooksearch.base.BaseViewModel
import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _changeDocumentAction = MutableLiveData<Event<Document>>()
    val changeDocumentAction: LiveData<Event<Document>>
        get() = _changeDocumentAction

    fun onDocumentChange(document: Document) {
        _changeDocumentAction.value = Event(document)
    }

}