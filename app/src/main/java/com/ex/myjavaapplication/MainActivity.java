package com.ex.myjavaapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MainActivity extends AppCompatActivity {

    private LocalTime fgTimeWhenStarted;
    private LocalTime bgTimeWhenStarted;
    private boolean wasPaused = false;

    private long elapsedFgTime = 0;
    private long elapsedBgTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v -> updateFgTimer());
        ((TextView)findViewById(R.id.textViewBg)).setText("Time spent in background:\n00:00");
        ((TextView)findViewById(R.id.textViewFg)).setText("Time spent in foreground:\n00:00");
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onResume() {
        super.onResume();
        fgTimeWhenStarted = LocalTime.now();
        if (wasPaused) {
            updateBgTimer();
            wasPaused = false;
        }
    }

    protected void onPause() {
        super.onPause();
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
    protected void updateFgTimer() {
        elapsedFgTime += ChronoUnit.SECONDS.between(fgTimeWhenStarted, LocalTime.now());
        fgTimeWhenStarted = LocalTime.now();
        String textViewFg = "Time spent in foreground:\n" + DateUtils.formatElapsedTime(elapsedFgTime);
        ((TextView)findViewById(R.id.textViewFg)).setText(textViewFg);
    }
}