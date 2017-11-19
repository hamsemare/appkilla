package com.example.hamsemare.appkiller;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    EditText newText;
    Button guessbutton;

    //Array of answers
    String[] answers = {"DIRTY", "BALL", "FISH", "CROSS", "PAIR", "JUMP", "DATE", "CHILDREN", "BAT", "CAN"};
    long done_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        done_time= System.currentTimeMillis() + 30*1000;

        guessbutton = (Button) findViewById(R.id.guessbutton);
        newText=(EditText) findViewById(R.id.guess1);
        guessbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });

        loadImages();

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView time = (TextView) findViewById(R.id.time);
                                long current_time = System.currentTimeMillis();
                                long time_left = done_time - current_time;
                                if (time_left>0) {
                                    time.setText(String.valueOf( time_left / 1000));
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    int index = 1;

    private void loadImages() {
        ImageView mImageView;
        mImageView = (ImageView) findViewById(R.id.img1);
        String variableValue = "img" + String.valueOf(index) + "level1";
        mImageView.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
        index++;
        if (index > 9) {
            index=1;
        }
    }

    private void checkAnswer() {
        String Answer = (String) newText.getText().toString();
        String answerUpperCase = Answer.toUpperCase();

        if (System.currentTimeMillis()>= done_time) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl= pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My wakelook");
            wl.acquire();

        }

        if (answerUpperCase.equals(answers[index-2])) {
            newText.setText("CORRECT!");
            //increment timer by 1 minute
            done_time=done_time+60000;
            loadImages();
        }

        else {
            newText.setText("WRONG");

        }
    }

}

