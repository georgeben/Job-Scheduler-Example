package com.kurobarabenjamingeorge.jobschedulerexample;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private RadioGroup networkOptions;
    private Switch deviceCharging;
    private JobScheduler mScheduler;

    private static final int JOB_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        networkOptions = findViewById(R.id.networkOptions);
        deviceCharging = findViewById(R.id.deviceChargingSwitch);
    }

    public void scheduleJob(View view) {
        int selectedNetworkID = networkOptions.getCheckedRadioButtonId();
        int networkType = JobInfo.NETWORK_TYPE_NONE;
        switch (selectedNetworkID){
            case R.id.none:
                networkType = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.any:
                networkType = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.wifi:
                networkType = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }

        ComponentName serviceName = new ComponentName(getPackageName(), SleepJobService.class.getName());
        JobInfo.Builder builder  = new JobInfo.Builder(JOB_ID, serviceName);
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        
        boolean constraintSet = (networkType != JobInfo.NETWORK_TYPE_NONE) || deviceCharging.isChecked() ;
        
        if (constraintSet){
            //Schedulr job
            Toast.makeText(this, "Job started", Toast.LENGTH_SHORT).show();
            builder.setRequiredNetworkType(networkType);
            builder.setRequiresCharging(deviceCharging.isChecked());
            mScheduler.schedule(builder.build());
        }else{
            Toast.makeText(this, "Please set a constraint", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelJob(View view) {
        if(mScheduler != null){
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(this, "Jobs have been cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
