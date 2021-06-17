package com.test.kakaobooksearch.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    protected val layoutRes: Int
) : AppCompatActivity() {

    private lateinit var binding: B
    abstract val viewModel: VM
    abstract val viewModelVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        with(binding) {
            lifecycleOwner = this@BaseActivity
            setVariable(viewModelVariable, viewModel)
        }
    }
}
