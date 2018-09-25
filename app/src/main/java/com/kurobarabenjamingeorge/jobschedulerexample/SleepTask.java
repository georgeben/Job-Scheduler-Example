package com.kurobarabenjamingeorge.jobschedulerexample;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by George Benjamin on 9/25/2018.
 */

public class SleepTask extends AsyncTask<Void, Void, Void> {
    private Context mContext;

    public SleepTask(Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.i("Job status", "The job has finished");
        Toast.makeText(mContext, "The sleeping job has finished", Toast.LENGTH_SHORT).show();
    }
}
