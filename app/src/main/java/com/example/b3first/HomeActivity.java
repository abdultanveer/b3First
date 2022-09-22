package com.example.b3first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
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

    @Override
    protected void onStart() {
        super.onStart();



        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, null,null,null,null);
        ListView cpListView = findViewById(R.id.cpListview);
        String[] columnNames = new String[]{"body","address"};
        int[] textviews = new int[]{android.R.id.text1,android.R.id.text2};
        CursorAdapter cursorAdapter =
                new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                        cursor,columnNames,textviews);
        cpListView.setAdapter(cursorAdapter);
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

  /*  public void insertContentprovider(View view) {
        Uri uriSms = Uri.parse("content://sync.todo/entry");
        ContentValues values = new ContentValues();
        values.put("title","b3first title");
        values.put("subtitle","b3first subtitle");
        getContentResolver().insert(uriSms,values);
    }*/
}