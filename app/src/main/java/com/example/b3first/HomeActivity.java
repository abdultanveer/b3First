package com.example.b3first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText etHome;
    TextView tvHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); //inflated

        etHome = findViewById(R.id.etHome);
        tvHome = findViewById(R.id.tvHome);

        //getIntent will give you hIntent which started this activity
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("nkey");

        tvHome.setText(name);
    }

    public void handleClicks(View view) {
        String name = etHome.getText().toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        //Intent  hIntent = new Intent();
    }
}