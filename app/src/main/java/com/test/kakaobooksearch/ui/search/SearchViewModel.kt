package com.test.kakaobooksearch.ui.search

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.kakaobooksearch.util.Event
import com.test.kakaobooksearch.base.BaseViewModel
import com.test.kakaobooksearch.base.Constants
import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.data.entities.KakaoBookReqModel
import com.test.kakaobooksearch.domain.GetSearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchBooksUseCase: GetSearchBooksUseCase) :
    BaseViewModel() {

    private val _documents = MutableLiveData<List<Document>>()
    val documents: LiveData<List<Document>>
        get() = _documents

    private val _hideKeyboard = MutableLiveData<Event<Unit>>()
    val hideKeyboard: LiveData<Event<Unit>>
        get() = _hideKeyboard

    private val _openBookDetail = MutableLiveData<Event<Document>>()
    val openBookDetail: LiveData<Event<Document>>
        get() = _openBookDetail

    //two way binding
    val searchKeyword = MutableLiveData<String>()

    private val reqModel = KakaoBookReqModel()
    private var nowTotalCount = 0

    // 더 불러오기
    val onLoad = {
        if (isNeedLoadMore()) {
            setPageUp()
            getSearchBooks(null)
        }
    }

    // 검색 이미지 클릭
    fun searchImageClicked() {
        _hideKeyboard.value = Event(Unit)
        onSearchProcess(searchKeyword.value, false)
    }

    // 키보드 검색 처리
    fun onEditorAction(actionId: Int): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            _hideKeyboard.value = Event(Unit)
            onSearchProcess(searchKeyword.value, false)
            return true
        }
        return false
    }

    // editText 감지
    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onSearchProcess(s.toString(), true)
    }

    // 리스트 아이템 클릭
    fun onBookItemClicked(document: Document) {
        _openBookDetail.value = Event(document)
    }

    // 상세 정보 반영 (좋아요)
    fun setDocumentChangeProcess(document: Document) {
        viewModelScope.launch(Dispatchers.IO) {
            _documents.value?.let {
                val itemList = it.toMutableList()
                for (index in itemList.indices) {
                    if (itemList[index].isbn == document.isbn) {
                        itemList[index] = document
                        withContext(Dispatchers.Main) {
                            _documents.value = itemList
                        }
                        return@let
                    }
                }
            }
        }
    }

    // 도서 검색하기
    private fun getSearchBooks(isAuto: Boolean?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val kakaoBook = getSearchBooksUseCase(reqModel.toMap())
                if (reqModel.page == 1) {
                    nowTotalCount = kakaoBook.meta.totalCount
                }
                if (kakaoBook.meta.totalCount == 0) {
                    isAuto?.let { flag ->
                        if (!flag) {
                            onShowToast("검색결과가 존재하지 않습니다.")
                        }
                    }
                    withContext(Dispatchers.Main) {
                        setResetData()
                    }
                } else {
                    val itemList = (_documents.value ?: listOf()).toMutableList()
                    itemList.addAll(kakaoBook.documents)
                    withContext(Dispatchers.Main) {
                        _documents.value = itemList
                    }
                }
            } catch (e: Exception) {
                Timber.d("Exceptions : ${e.message}")
                onShowToast("예기치 못한 오류가 발생하였습니다.")
            }
        }
    }

    private fun onSearchProcess(keyword: String?, isAuto: Boolean) {
        if (keyword.isNullOrEmpty()) {
            onShowToast("키워드를 입력해주세요")
            setResetData()
            return
        }
        setResetData()
        setSearchKeyword(keyword)
        getSearchBooks(isAuto)
    }

    private fun isNeedLoadMore(): Boolean {
        val itemList = (_documents.value ?: listOf()).toMutableList()
        return (nowTotalCount > itemList.size)
    }

    // reqModel page up
    private fun setPageUp() {
        reqModel.page = reqModel.page + 1
    }

    // editText 내용 세팅하기
    private fun setSearchKeyword(keyword: String) {
        reqModel.query = keyword
    }

    // reqModel reset
    private fun setResetData() {
        reqModel.page = Constants.DEFAULT_PAGE_VALUE
        val itemList = (_documents.value ?: listOf()).toMutableList()
        if (itemList.isNotEmpty()) {
            itemList.clear()
            _documents.value = itemList
        }
    }
}



