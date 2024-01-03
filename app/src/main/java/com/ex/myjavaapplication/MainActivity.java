package com.ex.myjavaapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MainActivity extends AppCompatActivity {

    private LocalTime foregroundTime;
    private LocalTime backgroundTime;
    private boolean wasPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v -> refreshTimer());
        ((TextView)findViewById(R.id.textViewBg)).setText("Time spent in background:\n00:00");
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
        foregroundTime = LocalTime.now();
        ((TextView)findViewById(R.id.textViewFg)).setText("Time spent in foreground:\n00:00");
        if (wasPaused) {
            updateBgTime();

        }
    }

    protected void onPause() {
        super.onPause();
        wasPaused = true;
        backgroundTime = LocalTime.now();
        ((TextView)findViewById(R.id.textViewBg)).setText("Time spent in background:\n00:00");
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void updateBgTime() {
        long elapsedBgTime = ChronoUnit.SECONDS.between(backgroundTime, LocalTime.now());
        String textViewBg = "Time spent in background:\n" + DateUtils.formatElapsedTime(elapsedBgTime);
        ((TextView)findViewById(R.id.textViewBg)).setText(textViewBg);
    }
    protected void refreshTimer() {
        long elapsedFgTime = ChronoUnit.SECONDS.between(foregroundTime, LocalTime.now());
        String textViewFg = "Time spent in foreground:\n" + DateUtils.formatElapsedTime(elapsedFgTime);
        ((TextView)findViewById(R.id.textViewFg)).setText(textViewFg);
    }
}