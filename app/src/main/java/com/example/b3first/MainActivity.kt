package com.example.b3first

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    lateinit var etName: EditText //declaration
    lateinit var conTextView: TextView
    var TAG = MainActivity::class.java.simpleName

    /**
     * memory is allocated for the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName) // instantiation
        conTextView = findViewById(R.id.tvContact)
        Log.i(TAG,"onCreate")
    }





    fun clickHandler(viewClicked: View) {

        when(viewClicked.id){
            R.id.btnHome ->{ startHomeActivity() }
            R.id.btnDial -> { startDial()}
            R.id.btnFcm -> {getRegnToken()}
        }
    }

    private fun getRegnToken() {
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token: String = task.getResult().toString()

                // Log and toast
                //val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, token)
                Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
            })
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