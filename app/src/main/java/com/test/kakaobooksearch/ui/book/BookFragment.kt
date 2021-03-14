package com.test.kakaobooksearch.ui.book

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
            backAction.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    requireActivity().onBackPressed()
                }
            })
            changeDocumentAction.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    sharedViewModel.onDocumentChange(it)
                }
            })
            openUrlAction.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    openUrl(it)
                }
            })
        }
    }

    // Url 외부 연결
    private fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}