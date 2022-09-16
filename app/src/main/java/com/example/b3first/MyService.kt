package com.example.b3first

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log


//playstore service
class MyService : Service() {

    // Binder given to clients[activities]
    private val binder: IBinder = LocalBinder()

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG , "service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        Log.i(TAG , "service started--"+intent?.getStringExtra("filename"))
        var mediaPlayer = MediaPlayer.create(this,R.raw.mymusic)
        mediaPlayer.start()
        return START_STICKY
    }

    //binder = contract == pipe b/w service and the activity
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG , "service destroyed")

    }

    companion object{
        var TAG =MyService::class.java.simpleName
    }

    fun  add( fno: Int,  sno: Int):Int{
        return fno + sno
    }

    fun getAds():String{
        return "airtickets.com"
    }

    fun getLocation():String {
        return "lat-long"
    }


    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): MyService = this@MyService
    }
}