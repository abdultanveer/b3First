package com.example.b3first

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var etName: EditText //declaration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName) // instantiation
    }

    fun clickHandler(viewClicked: View) {
        when(viewClicked.id){
            R.id.btnHome ->{         startHomeActivity()            }
            R.id.btnDial -> { startDial()}
        }
    }

    private fun startDial() {
        var dIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:98765432"))
        startActivity(dIntent)
    }

    private fun startHomeActivity() {
        //var name : String = etName.text.toString()
        var name = etName.text.toString()

        var hIntent = Intent(this, HomeActivity::class.java)
        hIntent.putExtra("nkey", name)
        startActivity(hIntent)

        //  Toast.makeText(this,name,Toast.LENGTH_SHORT).show()
    }


}