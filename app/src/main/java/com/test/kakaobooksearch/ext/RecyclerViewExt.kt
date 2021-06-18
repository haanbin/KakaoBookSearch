package com.test.kakaobooksearch.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.kakaobooksearch.ui.search.SearchAdapter
import com.test.kakaobooksearch.ui.search.SearchViewModel
import com.test.kakaobooksearch.util.EndlessRecyclerViewScrollListener
import com.test.kakaobooksearch.vo.DocumentVo

@BindingAdapter(
    "bind:searchRecyclerViewAttr",
    "bind:documentData"
)
fun RecyclerView.setUserRecyclerViewAttr(
    viewModel: SearchViewModel,
    documents: List<DocumentVo>?
) {
    if (itemAnimator != null) {
        itemAnimator = null
    }
    val adapter = adapter as? SearchAdapter ?: SearchAdapter(viewModel).also {
        this.adapter = it
    }
    documents?.let {
        adapter.submitList(it)
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
