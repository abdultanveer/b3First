package com.example.b3first;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

//string =url i/p type Integer = percentage - progress type, Bitmap -- image- resulttype

public class DownloadTask extends AsyncTask<String,Integer, Bitmap> {
    public static String TAG = DownloadTask.class.getSimpleName();
    ProgressBar progressBar;
   public  DownloadTask(ProgressBar mProgressBar){
       progressBar = mProgressBar;
   }


    @Override//will run on the background worker thread
    protected Bitmap doInBackground(String... url) {
        Log.i(TAG,"doInBackground--"+url[0]);
        for(int i=0; i<100;i++) {
            try {
                Thread.sleep(100);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override //onProgressUpdate runs on the ui thread
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }
}
