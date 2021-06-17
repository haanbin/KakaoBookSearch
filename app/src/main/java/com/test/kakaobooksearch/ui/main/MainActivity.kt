package com.test.kakaobooksearch.ui.main

import androidx.activity.viewModels
import com.test.kakaobooksearch.BR
import com.test.kakaobooksearch.R
import com.test.kakaobooksearch.base.BaseActivity
import com.test.kakaobooksearch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()
    override val viewModelVariable: Int
        get() = BR.vm
}
