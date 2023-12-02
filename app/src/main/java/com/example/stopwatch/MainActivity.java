package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int mseconds=0;
    boolean isrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);

        startTimer();
    }

    public void onstop (View view){
        isrunning=false;
    }
    public void onplay (View view){
        isrunning = true;
    }
    public void onreset (View view){
        isrunning = false;
        mseconds = 0;
    }

    public void startTimer() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int ms = mseconds%10;
                int s = mseconds/10;
                int m= s/60;
                int h = m/60; // or seconds/(60*60)

                if (isrunning) {
                    mseconds++;
                }
                String formatString = String.format(Locale.getDefault(), "%02d:%02d:%02d:%02d",h,m,s,ms);

                textView.setText(formatString);
                handler.postDelayed(this,100);
            }
        };
        handler.post(runnable);
    }
}