package com.example.b3first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText etHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); //inflated
        etHome = findViewById(R.id.etHome);
    }

    public void handleClicks(View view) {
        String name = etHome.getText().toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}