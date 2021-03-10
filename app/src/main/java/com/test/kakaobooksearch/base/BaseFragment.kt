package com.test.kakaobooksearch.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    protected val layoutRes: Int
) : Fragment() {

    protected lateinit var binding: B
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

    protected fun onBaseObserve() {
        viewModel.showToast.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                showToast(it)
            }
        })
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}