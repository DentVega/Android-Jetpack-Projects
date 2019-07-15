package com.brianvega.startupweekend.fragments.address

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.brianvega.startupweekend.R
import com.brianvega.startupweekend.fragments.BaseFragment
import com.brianvega.startupweekend.models.Event
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.CameraUpdateFactory

class AddressFragment : BaseFragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var viewModel: AddressViewModel
    private var currentLocation: LatLng? = LatLng(0.0, 0.0)
    private var googleMap: GoogleMap? = null
    private var mMapView: MapView? = null

    companion object {
        fun newInstance() = AddressFragment()
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.address_fragment, container, false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)
        mMapView = rootView.findViewById(R.id.map_address) as MapView
        mMapView!!.onCreate(savedInstanceState)
        mMapView!!.onResume()
        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMapView!!.getMapAsync { mMap ->
            googleMap = mMap
            googleMap!!.isMyLocationEnabled = true
            getLastLocation()
        }


        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddressViewModel::class.java)
        viewModel.events!!.observe(this, Observer { events ->
            addEventMarker(events)
        })
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            currentLocation = if (location != null) LatLng(location.latitude, location.longitude) else LatLng(0.0, 0.0)
            val cameraPosition = CameraPosition.Builder().target(currentLocation).zoom(12f).build()
            googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    private fun addEventMarker(events: MutableList<Event>) {
        events.map { event ->
            val sydney = LatLng(event.location.latitude, event.location.longitude)
            googleMap!!.addMarker(MarkerOptions().position(sydney).title(event.name).snippet(event.place))
        }
    }

    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView!!.onLowMemory()
    }

}