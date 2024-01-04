package com.ex.myjavaapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.widget.Chronometer;
import android.widget.TextView;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MainActivity extends AppCompatActivity {

    private LocalTime bgTimeWhenStarted;
    private boolean wasPaused = false;
    private long elapsedBgTime = 0;

    private Chronometer chronometer;
    private long pauseOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.fgChrono);
        ((TextView)findViewById(R.id.textViewBg)).setText("Time spent in background:\n00:00");
        ((TextView)findViewById(R.id.textViewFg)).setText("Time spent in foreground:");
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
        chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
        chronometer.start();
        if (wasPaused) {
            updateBgTimer();
            wasPaused = false;
        }
    }

    protected void onPause() {
        super.onPause();
        chronometer.stop();
        pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
        wasPaused = true;
        bgTimeWhenStarted = LocalTime.now();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void updateBgTimer() {
        elapsedBgTime += ChronoUnit.SECONDS.between(bgTimeWhenStarted, LocalTime.now());
        bgTimeWhenStarted = LocalTime.now();
        String textViewBg = "Time spent in background:\n" + DateUtils.formatElapsedTime(elapsedBgTime);
        ((TextView)findViewById(R.id.textViewBg)).setText(textViewBg);
    }
}