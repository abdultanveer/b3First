package com.example.b3first;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

//string =url i/p type Integer = percentage - progress type, Bitmap -- image- resulttype

public class DownloadTask extends AsyncTask<String,Integer, Bitmap> {
    public static String TAG = DownloadTask.class.getSimpleName();

   public  DownloadTask(ProgressBar mProgressBar){}


    @Override//will run on the background worker thread
    protected Bitmap doInBackground(String... url) {
        Log.i(TAG,"doInBackground--"+url[0]);
        publishProgress(50);
        return null;
    }
}
