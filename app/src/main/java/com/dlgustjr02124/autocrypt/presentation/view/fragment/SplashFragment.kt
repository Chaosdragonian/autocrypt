package com.dlgustjr02124.autocrypt.presentation.view.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dlgustjr02124.autocrypt.R
import com.dlgustjr02124.autocrypt.databinding.FragmentSplashBinding
import com.dlgustjr02124.autocrypt.presentation.base.BaseFragment
import com.dlgustjr02124.autocrypt.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment(override val layoutResourceId: Int = R.layout.fragment_splash) :
    BaseFragment<FragmentSplashBinding>() {
    val TAG = "SplashFragment"

    companion object {
        @JvmField
        val CENTER_LIST_SIZE = 100

        @JvmField
        val CENTER_PAGE_SIZE = 10
    }

    private val viewModel: SplashViewModel by viewModels()

    override fun initView() {

        viewDataBinding.viewModel = viewModel
        getCenterList(1)

        viewModel.startProgressTimer()
    }

    override fun setObserver() {
        with(viewModel) {
            centerListLiveData.observe(this@SplashFragment) {
                if (it.size == CENTER_LIST_SIZE) {
                    Log.d("centerListLiveData", "completeDownload")
                    setLoadComplete()
                }
            }
            progressValueLiveData.observe(this@SplashFragment) {
                Log.d("progressValueLiveData", it.toString())

                if (it == 100) {
                    val action = SplashFragmentDirections.actionSplashFragmentToMapFragment(
                        centerDataList = centerListLiveData.value?.toTypedArray() ?: arrayOf()
                    )
                    findNavController().navigate(action)
                }
            }
            nextCounterLiveData.observe(this@SplashFragment) {
                if (it < CENTER_PAGE_SIZE) {
                    getCenterList(it)
                }
            }
        }
    }

    private fun getCenterList(page: Int) {
        viewModel.getCenterList(page)
    }
}