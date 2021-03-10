package com.test.kakaobooksearch.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.kakaobooksearch.base.BaseViewModel
import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.ui.search.SearchAdapter
import com.test.kakaobooksearch.ui.search.SearchViewModel
import com.test.kakaobooksearch.util.EndlessRecyclerViewScrollListener


@BindingAdapter("bind:searchRecyclerViewAttrTest", "bind:onLoad")
fun RecyclerView.setUserRecyclerViewAttrTest(viewModel: BaseViewModel, onLoad: (() -> Unit)?) {
    if (viewModel is SearchViewModel) {
        this.itemAnimator = null
        val layoutManager = LinearLayoutManager(context)
        adapter = SearchAdapter(viewModel)
        this.layoutManager = layoutManager
        onLoad?.let {
            addOnScrollListener(
                EndlessRecyclerViewScrollListener(
                    layoutManager,
                    8,
                    it
                )
            )
        }
    }
}

@BindingAdapter("bind:documentData")
fun RecyclerView.setUserData(userFormats: List<Document>?) {
    val userAdapter = adapter as? SearchAdapter
    userFormats?.let { userAdapter?.setData(it) }
}
