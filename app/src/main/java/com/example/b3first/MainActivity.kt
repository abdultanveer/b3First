package com.example.b3first

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var etName: EditText //declaration
    lateinit var conTextView: TextView
    var TAG = MainActivity::class.java.simpleName
    lateinit var progressBar: ProgressBar

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





    fun clickHandler(viewClicked: View) {

        when(viewClicked.id){
            R.id.btnHome ->{         startHomeActivity()            }
            R.id.btnDial -> { startDial()}
            R.id.btnDownload -> {downloadImage()}
            R.id.btnCal -> {openCalendar()}
            R.id.btnIntent -> { handleIntent()}
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

    private fun add(fno: Int, sno: Int):Int {
      //  throw NullPointerException("cog ex thrown")
        return fno + sno
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        var contact = intent?.extras?.getString("ckey")
        conTextView.text = contact
    }


}