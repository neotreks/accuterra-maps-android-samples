package com.accuterra.google_maps_accuterra_maps_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.net.MalformedURLException
import java.net.URL

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Visit https://accuterra.com and create your free account to get a map API key.
        val accuterraMapsKey = "YOUR-MAP-API-KEY-HERE"

        mMap.mapType = GoogleMap.MAP_TYPE_NONE

        val tileProvider: TileProvider = object : UrlTileProvider(256, 256) {
            override fun getTileUrl(x: Int, y: Int, zoom: Int): URL? {

                /* Define the URL pattern for the tile images */
                val url = "https://maps.accuterra.com/v1/raster/accuterra-outdoors/$zoom/$x/$y.png?key=$accuterraMapsKey"
                return if (!checkTileExists(x, y, zoom)) {
                    null
                } else try {
                    URL(url)
                } catch (e: MalformedURLException) {
                    throw AssertionError(e)
                }
            }

            /*
             * Check that the tile server supports the requested x, y and zoom.
             * Complete this stub according to the tile range you support.
             * If you support a limited range of tiles at different zoom levels, then you
             * need to define the supported x, y range at each zoom level.
             */
            private fun checkTileExists(x: Int, y: Int, zoom: Int): Boolean {
                val minZoom = 8
                val maxZoom = 18
                return zoom in minZoom..maxZoom
            }
        }

        mMap.addTileOverlay(TileOverlayOptions().tileProvider(tileProvider))

        val centerPoint = LatLng(39.375239, -104.861077)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerPoint, 14f))
    }
}