package com.example.b3first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//havells appliances
public class HomeActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    EditText etHome;
    TextView tvHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); //inflated

        etHome = findViewById(R.id.etHome);
        tvHome = findViewById(R.id.tvHome);

        etHome.setOnFocusChangeListener(this); //wiring

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
            case R.id.btnFinish:
                closeSendData();
                break;
        }

        //Intent  hIntent = new Intent();
    }

    private void closeSendData() {

        //get the data from edittext
        String contact = etHome.getText().toString();
        //send the data to parent activity
        Intent cIntent = new Intent();
        cIntent.putExtra("ckey",contact);
        setResult(RESULT_OK,cIntent);
        //close this activity
        finish();
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

    @Override
    public void onFocusChange(View view, boolean isFocussed) {
        if(isFocussed){
            Toast.makeText(this, "focussed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "lost focus", Toast.LENGTH_SHORT).show();

        }
    }
}