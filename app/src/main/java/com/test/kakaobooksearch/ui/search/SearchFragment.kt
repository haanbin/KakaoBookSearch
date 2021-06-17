package com.test.kakaobooksearch.ui.search

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.kakaobooksearch.BR
import com.test.kakaobooksearch.R
import com.test.kakaobooksearch.base.BaseFragment
import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.databinding.FragmentSearchBinding
import com.test.kakaobooksearch.ext.keyboardIsVisible
import com.test.kakaobooksearch.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override val viewModel: SearchViewModel by viewModels()
    override val viewModelVariable: Int
        get() = BR.vm
    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onObserve() {
        with(viewModel) {
            hideKeyboard.observe(
                viewLifecycleOwner,
                { event ->
                    event.getContentIfNotHandled()?.let {
                        hideKeyboard()
                    }
                }
            )
            openBookDetail.observe(
                viewLifecycleOwner,
                { event ->
                    event.getContentIfNotHandled()?.let {
                        openBookFragment(it)
                    }
                }
            )
        }
        with(sharedViewModel) {
            changeDocumentAction.observe(
                viewLifecycleOwner,
                { event ->
                    event.getContentIfNotHandled()?.let {
                        viewModel.setDocumentChangeProcess(it)
                    }
                }
            )
        }
    }

    private fun openBookFragment(document: Document) {
        val action = SearchFragmentDirections.nextAction(document)
        findNavController().navigate(action)
    }

    private fun hideKeyboard() {
        requireActivity().currentFocus?.let {
            if (it.keyboardIsVisible()) {
                val inputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
                inputMethodManager.isAcceptingText
            }
        }
    }
}
