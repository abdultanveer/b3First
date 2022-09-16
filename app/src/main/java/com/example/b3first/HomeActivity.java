package com.example.b3first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    public static String TAG =HomeActivity.class.getSimpleName();
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

    public void serviceHandler(View view) {
        Intent serviceIntent = new Intent(HomeActivity.this,MyService.class);
        switch (view.getId()){
            case R.id.btnStart:
                serviceIntent.putExtra("filename","mymusic.mp3");
                startService(serviceIntent);
                break;
            case  R.id.btnStop:
                stopService(serviceIntent);
                break;
            case R.id.btnBind:
                bindService(serviceIntent,serviceConnection,BIND_AUTO_CREATE);
                break;
        }
    }



    ServiceConnection serviceConnection = new ServiceConnection() {
      //  MyService myService = new MyService();//creating a service - setting catering service
        MyService myService;
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            MyService.LocalBinder myLocalBinder = (MyService.LocalBinder) binder;
            myService = myLocalBinder.getService();
            int result = myService.add(888,999);
            Log.i(TAG,"result ="+ result);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}