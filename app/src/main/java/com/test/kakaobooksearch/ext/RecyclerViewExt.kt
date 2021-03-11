package com.test.kakaobooksearch.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.kakaobooksearch.base.BaseViewModel
import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.ui.search.SearchAdapter
import com.test.kakaobooksearch.ui.search.SearchViewModel
import com.test.kakaobooksearch.util.EndlessRecyclerViewScrollListener


@BindingAdapter(
    "bind:searchRecyclerViewAttrTest",
    "bind:documentData"
)
fun RecyclerView.setUserRecyclerViewAttrTest(
    viewModel: SearchViewModel,
    documents: List<Document>?
) {
    itemAnimator = null
    val adapter = adapter as? SearchAdapter ?: SearchAdapter(viewModel).also {
        this.adapter = it
    }
    documents?.let {
        adapter.setData(it)
    }
}

@BindingAdapter(
    "bind:onLoad",
    "bind:threshold"
)
fun RecyclerView.setLoadMore(onLoad: (() -> Unit)?, threshold: Int) {
    val layoutManager = this.layoutManager ?: LinearLayoutManager(context)
    onLoad?.let {
        addOnScrollListener(
            EndlessRecyclerViewScrollListener(
                layoutManager,
                threshold,
                it
            )
        )
    }
}