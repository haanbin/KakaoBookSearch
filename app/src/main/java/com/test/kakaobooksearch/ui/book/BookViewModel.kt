package com.test.kakaobooksearch.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.test.kakaobooksearch.util.Event
import com.test.kakaobooksearch.base.BaseViewModel
import com.test.kakaobooksearch.data.entities.Document
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(handle: SavedStateHandle) : BaseViewModel() {

    private val _document = MutableLiveData<Document>()
    val document: LiveData<Document>
        get() = _document

    private val _isLike = MutableLiveData<Boolean>()
    val isLike: LiveData<Boolean>
        get() = _isLike

    private val _backAction = MutableLiveData<Event<Unit>>()
    val backAction: LiveData<Event<Unit>>
        get() = _backAction

    private val _openUrlAction = MutableLiveData<Event<String>>()
    val openUrlAction: LiveData<Event<String>>
        get() = _openUrlAction

    private val _changeDocumentAction = MutableLiveData<Event<Document>>()
    val changeDocumentAction: LiveData<Event<Document>>
        get() = _changeDocumentAction

    init {
        val document = handle.get("document") as? Document
        document?.let {
            _document.value = it
            _isLike.value = it.isLike
        } ?: run {
            onShowToast("상세정보가 정확하지 않습니다.")
            _backAction.value = Event(Unit)
        }
    }

    // 좋아요 클릭
    fun onLikeClicked() {
        _isLike.value?.let { isLike ->
            _isLike.value = !isLike
            _document.value?.let {
                it.isLike = !isLike
                _changeDocumentAction.value = Event(it)
            }
        }
    }

    // 뒤로가기 클릭
    fun onBackClicked() {
        _backAction.value = Event(Unit)
    }

    //URL 연결
    fun onOpenUrl(url: String) {
        if (url.isNotEmpty()) {
            _openUrlAction.value = Event(url)
        }
    }
}