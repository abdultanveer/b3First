package com.example.b3first

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.webkit.WebView
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationActivity : AppCompatActivity() {
var MY_PERMISSIONS_REQUEST_LOCATION= 123
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val myWebView: WebView = findViewById(R.id.webView)

        val unencodedHtml =
            "<html><body>'%23' is the percent code for ‘#‘ </body></html>";
        val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
        myWebView.loadData(encodedHtml, "text/html", "base64")
    }

    @SuppressLint("MissingPermission")
    fun getLocation(view: View) {
        fusedLocationClient.lastLocation.addOnSuccessListener {
                location: Location? ->
            Log.i(TAG,"latitude="+location?.latitude+"--longitude="+location?.longitude)
        }
    }

    private fun requestLocationPermission() {
        Log.d("Pineapple", "Setting Permissions")

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_LOCATION)
    }


    companion object{
        var TAG = LocationActivity::class.java.simpleName
    }



}