package com.test.kakaobooksearch.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.test.kakaobooksearch.ext.showToast

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    protected val layoutRes: Int
) : Fragment() {

    private lateinit var binding: B
    abstract val viewModel: VM
    abstract val viewModelVariable: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            setVariable(viewModelVariable, viewModel)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBaseObserve()
        onObserve()
    }

    private fun onBaseObserve() {
        with(viewModel) {
            showToast.observe(
                viewLifecycleOwner,
                { event ->
                    event.getContentIfNotHandled()?.let {
                        showToast(it)
                    }
                }
            )
            showToastStringRes.observe(
                viewLifecycleOwner,
                { event ->
                    event.getContentIfNotHandled()?.let {
                        showToast(getString(it))
                    }
                }
            )
        }
    }

    private fun showToast(msg: String) {
        requireContext().showToast(msg)
    }

    abstract fun onObserve()
}
