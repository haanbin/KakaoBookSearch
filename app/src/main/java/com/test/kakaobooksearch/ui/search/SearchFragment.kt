package com.test.kakaobooksearch.ui.search

import androidx.fragment.app.viewModels
import com.test.kakaobooksearch.BR
import com.test.kakaobooksearch.R
import com.test.kakaobooksearch.base.BaseFragment
import com.test.kakaobooksearch.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override val viewModel: SearchViewModel by viewModels()
    override val viewModelVariable: Int
        get() = BR.vm

    override fun start() {

    }
}