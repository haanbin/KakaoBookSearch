package com.test.kakaobooksearch.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.kakaobooksearch.base.BaseViewModel
import com.test.kakaobooksearch.base.Constants
import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.data.entities.KakaoBookReqModel
import com.test.kakaobooksearch.domain.GetSearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchBooksUseCase: GetSearchBooksUseCase) :
    BaseViewModel() {

    private val _documents = MutableLiveData<List<Document>>()
    val documents: LiveData<List<Document>>
        get() = _documents

    //two way binding
    val searchKeyword = MutableLiveData<String>()

    private val reqModel = KakaoBookReqModel()
    private var nowTotalCount = 0

    // 더 불러오기
    val onLoad = {
        if (isNeedLoadMore()) {
            setPageUp()
            getSearchBooks()
        }
    }

    // 검색 이미지 클릭
    fun searchImageClicked() {
        if (searchKeyword.value.isNullOrEmpty()) {
            onShowToast("키워드를 입력해주세요")
            setResetData()
            return
        }
        searchKeyword.value?.let {
            setResetData()
            setSearchKeyword(it)
            getSearchBooks()
        }
    }

    // 도서 검색하기
    private fun getSearchBooks() {
        getSearchBooksUseCase(getReqModelToMap())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        if (reqModel.page == 1) {
                            nowTotalCount = it.meta.totalCount
                        }
                        if (it.meta.totalCount == 0) {
                            onShowToast("검색결과가 존재하지 않습니다.")
                            setResetData()
                        } else {
                            val itemList = (_documents.value ?: listOf()).toMutableList()
                            itemList.addAll(it.documents)
                            _documents.value = itemList
                        }
                    }
                } else {
                    onShowToast("예기치 못한 오류가 발생하였습니다.")
                }
            }, {
                it.message?.let { msg ->
                    onShowToast(msg)
                } ?: onShowToast("예기치 못한 오류가 발생하였습니다.")
            })
            .addTo(compositeDisposable)
    }

    private fun isNeedLoadMore() = (nowTotalCount > reqModel.page * reqModel.size)

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

    // convert 데이터 모델 to MAP
    private fun getReqModelToMap(): Map<String, String> {
        val map = hashMapOf<String, String>()
        map[Constants.QUERY] = reqModel.query
        map[Constants.PAGE] = reqModel.page.toString()
        map[Constants.SIZE] = reqModel.size.toString()
        return map
    }
}