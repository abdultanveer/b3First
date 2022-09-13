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
    TextView tvHome,tvAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); //inflated

        etHome = findViewById(R.id.etHome);
        tvHome = findViewById(R.id.tvHome);
        tvAuthor= findViewById(R.id.tvAuthor);

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
           // createAlarm("b3 android",20,59);
                searchBooks();
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


   public void searchBooks(){
       String bookName = etHome.getText().toString();
       new FetchBook(tvHome,tvAuthor).execute(bookName);

   }
}