package com.dlgustjr02124.autocrypt.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.dlgustjr02124.autocrypt.R
import com.dlgustjr02124.autocrypt.databinding.ActivityMainBinding
import com.dlgustjr02124.autocrypt.presentation.base.BaseActivity
import com.dlgustjr02124.autocrypt.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity(override val layoutResourceId: Int = R.layout.activity_main) :
    BaseActivity<ActivityMainBinding>() {
    private val viewModel: MainViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        viewDataBinding.viewModel = viewModel

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

    }

    override fun setObserver() {

    }
}