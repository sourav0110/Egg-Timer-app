package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    Button controllerButton;
    Boolean counterIsActive=false;
    CountDownTimer countDownTimer;

    public void updateTimer(int secondleft){
        int minutes=(int)secondleft/60;
        int seconds=(int)(secondleft-(minutes*60));
        String secondString=Integer.toString(seconds);
        String minString=Integer.toString(minutes);
        if(minString.length()==1)
            minString = '0'+minString;
        if(secondString.length() == 1 ) {
            if (seconds > 10)
                secondString = secondString + '0';
            else
                secondString = '0' + secondString;
        }
        timerTextView.setText(minString+":"+secondString);

    }
    public void controlTimer(View view){



        if(counterIsActive==false) {
            timerSeekBar.setEnabled(false);
            counterIsActive=true;
     

           countDownTimer=new CountDownTimer(timerSeekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    Log.i("finished", "timer done");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();

                }
            }.start();
        }
        else
        {
            counterIsActive=false;
            timerTextView.setText("00:30");
            timerSeekBar.setProgress(30);
            timerSeekBar.setEnabled(true);
            countDownTimer.cancel();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerSeekBar=(SeekBar)findViewById(R.id.timerSeekBar);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}