package com.test.kakaobooksearch.ui.book

import androidx.fragment.app.viewModels
import com.test.kakaobooksearch.BR
import com.test.kakaobooksearch.R
import com.test.kakaobooksearch.base.BaseFragment
import com.test.kakaobooksearch.databinding.FragmentBookBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : BaseFragment<FragmentBookBinding, BookViewModel>(R.layout.fragment_book) {


    override val viewModel: BookViewModel by viewModels()
    override val viewModelVariable: Int
        get() = BR.vm

    override fun start() {

    }
}