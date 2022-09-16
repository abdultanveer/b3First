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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    lateinit var etName: EditText //declaration
    lateinit var tvContact: TextView
    lateinit var etPassword:EditText
    lateinit var conTextView: TextView
    var TAG = MainActivity::class.java.simpleName

    /**
     * memory is allocated for the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName) // instantiation
        etPassword = findViewById(R.id.etPassword)
        conTextView = findViewById(R.id.tvContact)
        tvContact = findViewById(R.id.tvContact)
        Log.i(TAG,"onCreate")
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    private fun saveData() {
        //get the data from the edittexts
        var name = etName.text.toString();
        var password = etPassword.text.toString()
        //create a file
        var sharedPreferences = getSharedPreferences("cogsharedprefs", MODE_PRIVATE)
        //open the file in edit mode
        var editor =  sharedPreferences.edit()
        //write to the file
        editor.putString("nkey",name)
        editor.putString("pkey",password)
        //save the file
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        restoreData()
    }

    private fun restoreData() {
        //open the file
        var sharedPreferences = getSharedPreferences("cogsharedprefs", MODE_PRIVATE)
        //read from the file
        var name = sharedPreferences.getString("nkey","")
        var pass = sharedPreferences.getString("pkey","")
        //put the data into the edittexts
        etName.setText(name)
        etPassword.setText(pass)
    }


    fun clickHandler(viewClicked: View) {

        when(viewClicked.id){
            R.id.btnHome ->{         startHomeActivity()            }
            R.id.btnDial -> { startDial()}
        }
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

    fun firestoreHandler(view: View) {
        when(view.id){
            R.id.btnPut ->{ putFirestore()}
            R.id.btnGet->{ getDataFirestore()}
        }
    }

    private fun getDataFirestore() {
        val db = Firebase.firestore
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    private fun putFirestore() {
        var name = etName.text.toString()
        var pass = etPassword.text.toString()

        val db = Firebase.firestore
        val user = hashMapOf(
            "first" to name,
            "last" to pass,
        )

// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }


}