package com.test.kakaobooksearch.ui.search

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.kakaobooksearch.base.BaseViewModel
import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.data.entities.KakaoBookReqModel
import com.test.kakaobooksearch.domain.GetSearchBooksUseCase
import com.test.kakaobooksearch.util.Constants
import com.test.kakaobooksearch.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
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

    // 이미 검색중이 항목이 있으면 취소를 위함
    private var job: Job = Job()

    //two way binding
    val searchKeyword = MutableLiveData<String>()

    // API 요청 모델
    private val reqModel = KakaoBookReqModel()

    // 페이징 가능한 카운트
    private var pageableCount = 0

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
        viewModelScope.launch(Dispatchers.Default) {
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
        job = viewModelScope.launch {
            try {
                val kakaoBook = getSearchBooksUseCase(reqModel.toMap())
                if (reqModel.page == 1) {
                    pageableCount = kakaoBook.meta.pageableCount
                }
                if (kakaoBook.meta.totalCount == 0) {
                    isAuto?.let { flag ->
                        if (!flag) {
                            onShowToast("검색결과가 존재하지 않습니다.")
                        }
                    }
                    setResetData()
                } else {
                    val itemList = (_documents.value ?: listOf()).toMutableList()
                    itemList.addAll(kakaoBook.documents)
                    _documents.value = itemList
                }
            } catch (e: CancellationException) {
                Timber.d("CancellationException : ${e.message}")
            } catch (e: Exception) {
                Timber.d("Exceptions : ${e.message}")
                withContext(Dispatchers.Main) {
                    onShowToast("예기치 못한 오류가 발생하였습니다.")
                }
            }
        }
    }

    // 검색 프로세스
    private fun onSearchProcess(keyword: String?, isAuto: Boolean) {
        // 실행중인 검색 작업이 있으면 제거
        if (job.isActive) {
            job.cancel()
        }
        if (keyword.isNullOrEmpty()) {
            onShowToast("키워드를 입력해주세요")
            setResetData()
            return
        }
        setResetData()
        setSearchKeyword(keyword)
        getSearchBooks(isAuto)
    }

    // paging 호출 판단
    private fun isNeedLoadMore() = (pageableCount > reqModel.page * reqModel.size)

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



