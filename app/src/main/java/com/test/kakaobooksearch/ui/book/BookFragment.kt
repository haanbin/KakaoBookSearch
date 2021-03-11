package com.test.kakaobooksearch.ui.book

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.test.kakaobooksearch.BR
import com.test.kakaobooksearch.R
import com.test.kakaobooksearch.base.BaseFragment
import com.test.kakaobooksearch.databinding.FragmentBookBinding
import com.test.kakaobooksearch.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : BaseFragment<FragmentBookBinding, BookViewModel>(R.layout.fragment_book) {


    override val viewModel: BookViewModel by viewModels()
    override val viewModelVariable: Int
        get() = BR.vm
    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onObserve() {
        with(viewModel) {
            backAction.observe(viewLifecycleOwner, Observer { event ->
                event.getContentIfNotHandled()?.let {
                    requireActivity().onBackPressed()
                }
            })
            changeDocumentAction.observe(viewLifecycleOwner, Observer { event ->
                event.getContentIfNotHandled()?.let {
                    sharedViewModel.onDocumentChange(it)
                }
            })
        }
    }
}