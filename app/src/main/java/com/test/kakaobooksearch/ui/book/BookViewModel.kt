package com.test.kakaobooksearch.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.test.kakaobooksearch.Event
import com.test.kakaobooksearch.base.BaseViewModel
import com.test.kakaobooksearch.data.entities.Document
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val handle: SavedStateHandle) : BaseViewModel() {

    private val _document = MutableLiveData<Document>()
    val document: LiveData<Document>
        get() = _document

    private val _backAction = MutableLiveData<Event<Unit>>()
    val backAction: LiveData<Event<Unit>>
        get() = _backAction

    init {
        val document = handle.get("document") as? Document
        document?.let {
            _document.value = it
        } ?: run {
            onShowToast("상세정보가 정확하지 않습니다.")
            _backAction.value = Event(Unit)
        }
    }

    // 좋아요 클릭
    fun onLikeClicked() {
        _document.value?.let {
            it.isLike = !it.isLike
            _document.value = it
        }
    }

    // 뒤로가기 클릭
    fun onBackClicked() {
        _backAction.value = Event(Unit)
    }
}