package com.ex.myjavaapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Chronometer bgChronometer;
    private long bgPauseOffset = 0;
    private Chronometer fgChronometer;
    private long fgPauseOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fgChronometer = findViewById(R.id.fgChrono);
        bgChronometer = findViewById(R.id.bgChrono);
        ((TextView)findViewById(R.id.textViewBg)).setText("Time spent in background:");
        ((TextView)findViewById(R.id.textViewFg)).setText("Time spent in foreground:");
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
        fgChronometer.setBase(SystemClock.elapsedRealtime() - fgPauseOffset);
        fgChronometer.start();

        bgChronometer.stop();
        bgPauseOffset = SystemClock.elapsedRealtime() - bgChronometer.getBase();
        bgChronometer.setBase(SystemClock.elapsedRealtime() - bgPauseOffset);
    }

    protected void onPause() {
        super.onPause();
        bgChronometer.setBase(SystemClock.elapsedRealtime() - bgPauseOffset);
        bgChronometer.start();

        fgChronometer.stop();
        fgPauseOffset = SystemClock.elapsedRealtime() - fgChronometer.getBase();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}