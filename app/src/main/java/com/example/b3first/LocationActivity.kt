package com.example.b3first

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var MY_PERMISSIONS_REQUEST_LOCATION = 123;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    @SuppressLint("MissingPermission")
    fun getLocation(view: View) {
        requestLocationPermission()
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


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("Pineapple", "$requestCode")
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        Log.d("Pineapple", "Permission Granted!")
                        fusedLocationClient.lastLocation
                            .addOnSuccessListener { location ->
                                if (location != null) {
                                    Log.d("Pineapple", "${location.latitude}--${location.longitude}")
                                    // use your location object
                                    // get latitude , longitude and other info from this
                                    Toast.makeText(
                                        this,
                                        "${location.latitude}--${location.longitude}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    } else {
                        Log.d("Pineapple", "Permission was not granted")
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Log.d("Pineapple", "Permission Denied")

                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                }
            }
        }

    }



    companion object{
        var TAG = LocationActivity::class.java.simpleName
    }



}