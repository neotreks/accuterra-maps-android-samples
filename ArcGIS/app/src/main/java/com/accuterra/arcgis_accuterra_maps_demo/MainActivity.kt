package com.accuterra.arcgis_accuterra_maps_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.geometry.SpatialReferences
import com.esri.arcgisruntime.layers.WebTiledLayer
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.Basemap
import com.esri.arcgisruntime.mapping.view.MapView

class MainActivity : AppCompatActivity() {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get reference to map view
        mapView = findViewById(R.id.mapView)

        // Visit https://accuterra.com and create your free account to get a map API key.
        val accuterraMapsKey = "YOUR-MAP-API-KEY-HERE"

        val url = "https://maps.accuterra.com/v1/raster/accuterra-outdoors/{level}/{col}/{row}.png?key=$accuterraMapsKey"
        val webTiledLayer = WebTiledLayer(url)

        // Use web tiled layer as Basemap
        val map = ArcGISMap(Basemap(webTiledLayer))
        mapView.map = map

        // Custom attributes
        webTiledLayer.attribution = "Map tiles by AccuTerra Maps"

        // Set custom view point
        val point = Point(-104.861077, 39.375239, SpatialReferences.getWgs84())
        mapView.setViewpointCenterAsync(point, 5000.0)
    }

    override fun onPause() {
        super.onPause()
        mapView.pause()
    }
    override fun onResume() {
        super.onResume()
        mapView.resume()
    }
    override fun onDestroy() {
        super.onDestroy()
        mapView.dispose()
    }
}