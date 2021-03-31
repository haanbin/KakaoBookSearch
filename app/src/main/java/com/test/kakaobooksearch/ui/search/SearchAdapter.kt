package com.test.kakaobooksearch.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.databinding.ItemSearchBinding

class SearchAdapter(private val viewModel: SearchViewModel) :
    ListAdapter<Document, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindTo(getItem(position), viewModel)
    }

    class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(document: Document, viewModel: SearchViewModel) {
            with(binding) {
                model = document
                vm = viewModel
                executePendingBindings()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Document>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldConcert: Document,
                newConcert: Document
            ) = oldConcert.isbn == newConcert.isbn

            override fun areContentsTheSame(
                oldConcert: Document,
                newConcert: Document
            ) = oldConcert == newConcert
        }
    }
}