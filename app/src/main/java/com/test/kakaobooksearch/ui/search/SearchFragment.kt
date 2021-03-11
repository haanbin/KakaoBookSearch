package com.test.kakaobooksearch.ui.search

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.kakaobooksearch.BR
import com.test.kakaobooksearch.R
import com.test.kakaobooksearch.base.BaseFragment
import com.test.kakaobooksearch.data.entities.Document
import com.test.kakaobooksearch.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override val viewModel: SearchViewModel by viewModels()
    override val viewModelVariable: Int
        get() = BR.vm

    override fun onObserve() {
        with(viewModel) {
            hideKeyboard.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    hideKeyboard()
                }
            })
            openBookDetail.observe(viewLifecycleOwner, { event ->
                event.getContentIfNotHandled()?.let {
                    openBookFragment(it)
                }
            })
        }
    }

    private fun openBookFragment(document: Document){
        val action = SearchFragmentDirections.nextAction(document)
        findNavController().navigate(action)
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            (view ?: View(requireContext())).windowToken, 0
        )
    }
}