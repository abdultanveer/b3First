package com.example.b3first

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

    fun clickHandler(view: View) {
        //var name : String = etName.text.toString()
        var name = etName.text.toString()

        Toast.makeText(this,name,Toast.LENGTH_SHORT).show()
    }


}