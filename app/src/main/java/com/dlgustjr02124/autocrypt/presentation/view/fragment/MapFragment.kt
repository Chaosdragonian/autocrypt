package com.dlgustjr02124.autocrypt.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dlgustjr02124.autocrypt.R
import com.dlgustjr02124.autocrypt.data.model.NetworkResponse
import com.dlgustjr02124.autocrypt.databinding.CenterInfoItemBinding
import com.dlgustjr02124.autocrypt.databinding.FragmentMapBinding
import com.dlgustjr02124.autocrypt.presentation.base.BaseFragment
import com.dlgustjr02124.autocrypt.presentation.viewmodel.MapViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment(override val layoutResourceId: Int = R.layout.fragment_map) :
    BaseFragment<FragmentMapBinding>() {
    private val viewModel: MapViewModel by viewModels()
    private val args: MapFragmentArgs by navArgs()
    private lateinit var mapView: MapView
    private lateinit var locationSource: FusedLocationSource
    private lateinit var callback: OnBackPressedCallback

    private var centerList: Array<NetworkResponse.Data>? = null
    private var pressedTime: Long = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = viewDataBinding.mapView
        mapView.onCreate(savedInstanceState)
        centerList = args.centerDataList
        mapView.getMapAsync {
            viewModel.setNaverMapObject(it)
        }
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    override fun initView() {
        viewDataBinding.viewModel = viewModel
        viewDataBinding.view = this

    }

    override fun setObserver() {
        with(viewModel) {
            toastLiveData.observe(this@MapFragment) {
                Toast(requireContext()).apply {
                    setGravity(Gravity.CENTER, 0, 0)
                    duration = Toast.LENGTH_LONG
                    setText(it)
                }.show()
            }

            naverMapObjectLivedata.observe(this@MapFragment) {
                it.locationSource = locationSource
                it.locationTrackingMode = LocationTrackingMode.Follow
                val infoWindow = InfoWindow()
                it.setOnMapClickListener { _, _ ->
                    infoWindow.close()
                }
                for (center in centerList ?: arrayOf()) {
                    it?.let { naverMap ->
                        val marker = com.naver.maps.map.overlay.Marker()
                        when (center.centerType) {
                            "중앙/권역" -> marker.icon = com.naver.maps.map.util.MarkerIcons.RED
                            "지역" -> marker.icon = com.naver.maps.map.util.MarkerIcons.BLUE
                            else -> marker.icon = com.naver.maps.map.util.MarkerIcons.GREEN
                        }
                        marker.position = com.naver.maps.geometry.LatLng(
                            center.lat.toDouble(),
                            center.lng.toDouble()
                        )
                        marker.tag = center

                        marker.setOnClickListener {
                            if (marker.infoWindow == null) {
                                infoWindow.adapter =
                                    object : InfoWindow.DefaultViewAdapter(requireContext()) {
                                        override fun getContentView(infoWindow: InfoWindow): View {
                                            return View.inflate(
                                                requireContext(),
                                                R.layout.center_info_item,
                                                null
                                            ).apply {
                                                val binding: CenterInfoItemBinding =
                                                    CenterInfoItemBinding.bind(this)
                                                val centerData = marker.tag as NetworkResponse.Data
                                                binding.centerAddress.text = centerData.address
                                                binding.centerName.text = centerData.centerName
                                                binding.facilityName.text = centerData.facilityName
                                                binding.phoneNumber.text = centerData.phoneNumber
                                                binding.updateDate.text = centerData.updatedAt
                                            }
                                        }
                                    }
                                infoWindow.open(marker)
                                naverMap.moveCamera(CameraUpdate.scrollTo(marker.position))
                            } else {
                                infoWindow.close()
                            }
                            true
                        }
                        marker.map = naverMap
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (pressedTime + 2000 > System.currentTimeMillis()) {
                    requireActivity().finish()
                } else {
                    viewModel.setToast(getString(R.string.back_button_click))
                }
                pressedTime = System.currentTimeMillis();
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    fun cameraToLocation() {
        viewModel.naverMapObjectLivedata.value?.moveCamera(
            CameraUpdate.scrollTo(
                LatLng(
                    locationSource.lastLocation?.latitude ?: 0.0,
                    locationSource.lastLocation?.longitude ?: 0.0
                )
            )
        )
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions,
                grantResults
            )
        ) {
            if (!locationSource.isActivated) { // 권한 거부됨
                viewModel.naverMapObjectLivedata.value?.locationTrackingMode =
                    LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}