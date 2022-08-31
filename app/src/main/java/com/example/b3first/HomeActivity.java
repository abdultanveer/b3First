package com.example.b3first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
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
        if(getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String name = bundle.getString("nkey");
            tvHome.setText(name);
        }

    }

    public void handleClicks(View viewClicked) {
        switch (viewClicked.getId()){
            case R.id.btnAlarm:
            createAlarm("b3 android",20,59);
                break;
        }

        //Intent  hIntent = new Intent();
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}