package com.kurobarabenjamingeorge.jobschedulerexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

/**
 * Created by George Benjamin on 9/25/2018.
 * 
 * This creates an AsyncTask that sleeps for 5 seconds
 */


public class SleepJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        new SleepTask(getApplicationContext()).execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Toast.makeText(this, R.string.job_failed, Toast.LENGTH_SHORT).show();
        return false;
    }
    
}
