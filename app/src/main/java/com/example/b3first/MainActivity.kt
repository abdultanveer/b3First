package com.example.b3first

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {

    lateinit var etName: EditText //declaration
    lateinit var conTextView: TextView
    var TAG = MainActivity::class.java.simpleName
    lateinit var progressBar: ProgressBar
    lateinit var smsReceiver: SmsReceiver

    /**
     * memory is allocated for the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName) // instantiation
        conTextView = findViewById(R.id.tvContact)
        progressBar = findViewById(R.id.progressBar)
        Log.i(TAG,"onCreate")
    }

    override fun onStart() {
        super.onStart()
        //register in activity
        smsReceiver = SmsReceiver()
        var intentFilter = IntentFilter()
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED")
        //intentFilter.addCategory()
        registerReceiver(smsReceiver,intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        //unregister broadcastreciever
        unregisterReceiver(smsReceiver)

    }




    fun clickHandler(viewClicked: View) {

        when(viewClicked.id){
            R.id.btnHome ->{         startHomeActivity()            }
            R.id.btnDial -> { startDial()}
            R.id.btnDownload -> {downloadImage()}
            R.id.btnCal -> {openCalendar()}
            R.id.btnIntent -> { handleIntent()}
            R.id.btnNotification -> { showNotification()}
        }
    }

    private fun showNotification() {
        createNotificationChannel()
        val intent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        //pending intent is an intent fired by the os at later point in time on behalf of ur app
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)




        var builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .setContentTitle("cognizant android")
            .setContentText("batch 3 ")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .addAction(
                com.google.android.material.R.drawable.ic_keyboard_black_24dp, "call",
                pendingIntent)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(123, builder.build())
        }

    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "CHANNEL_ID"
                //getString(R.string.channel_name)
            val descriptionText = "description of notification channel- cognizant"
                //getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun handleIntent() {
        var mIntent = Intent(Intent.ACTION_VIEW,Uri.parse("http:yahoo.com"))
                startActivity(mIntent)

        //"tel:12345678"))
    }

    private fun openCalendar() {
        var cIntent = Intent("com.cognizant.abdul")
        startActivity(cIntent)
    }

    private fun downloadImage() {
        var downloadTask = DownloadTask(progressBar)
        downloadTask.execute("https://someimage.com")
    }

    private fun startDial() {
        //implicit intent
        var dIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:98765432"))
        startActivity(dIntent)
    }

    private fun startHomeActivity() {
        //var name : String = etName.text.toString()
        var result =  add(10,20)
        var name = etName.text.toString()
        //explicit intent
        var hIntent = Intent(this, HomeActivity::class.java)
       // hIntent.putExtra("nkey", name)
        startActivityForResult(hIntent,123)

        //  Toast.makeText(this,name,Toast.LENGTH_SHORT).show()
    }
//singleton design pattern
    private fun add(fno: Int, sno: Int):Int {
      //  throw NullPointerException("cog ex thrown")
        var smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage("5556",
            "COG-SRV","welcome",
        null,null)
        return fno + sno

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        var contact = intent?.extras?.getString("ckey")
        conTextView.text = contact
    }


}