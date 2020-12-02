package com.accuterra.mapbox_accuterra_maps_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMapOptions

class MainActivity : AppCompatActivity() {
    private var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))

        val options = MapboxMapOptions.createFromAttributes(this, null)
            .camera(
                CameraPosition.Builder()
                    .target(LatLng(39.375239, -104.861077))
                    .zoom(14.0)
                    .build()
            )

        mapView = MapView(this, options)
        mapView?.onCreate(savedInstanceState)

        // Visit https://accuterra.com and create your free account to get a map API key.
        val accuterraMapsKey = "YOUR-MAP-API-KEY-HERE"

        mapView?.getMapAsync { mapboxMap ->
            mapboxMap.setStyle("https://maps.accuterra.com/v1/styles/accuterra-outdoors/style.json?key=$accuterraMapsKey") {
                // Map is set up and the style has loaded. Now you can add data or make other map adjustments.
            }
        }

        setContentView(mapView)
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
}