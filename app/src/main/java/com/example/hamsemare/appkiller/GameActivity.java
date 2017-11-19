package com.example.hamsemare.appkiller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity {

    HashMap<Integer, String> hmap = new HashMap<Integer, String>();

    EditText newText;
    Button GuessButton;
    long done_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        hmap.put(1,"DIRTY");
        hmap.put(2,"BALL");
        hmap.put(3,"FISH");
        hmap.put(4,"CROSS");
        hmap.put(5,"PAIR");
        hmap.put(6,"JUMP");
        hmap.put(7,"DATE");
        hmap.put(8,"CHILDREN");
        hmap.put(9,"BAT");
        hmap.put(10,"CAN");

        done_time = System.currentTimeMillis() + 30*1000;

         newText = (EditText) findViewById(R.id.guess);


         GuessButton = (Button) findViewById(R.id.guessButton);
         GuessButton.setOnClickListener(new View.OnClickListener()  {
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
                                EditText Timer = (EditText)findViewById(R.id.Timer);
                                long current_time = System.currentTimeMillis();
                                long time_left = done_time - current_time;
                                if (time_left>0) {
                                    Timer.setText(String.valueOf( time_left / 1000));
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

    int index = 3 ;
    private void loadImages() {
        ImageView mImageView;
        mImageView = (ImageView) findViewById(R.id.img1);
        String variableValue = "img" + String.valueOf(index) + "level1";
        mImageView.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
        index++;
        if(index > 9){
            index = 1;
        }
    }

    private void checkAnswer() {

        String Answer = (String) newText.getText().toString();
        String answerUpperCase = Answer.toUpperCase();
        if (answerUpperCase.equals(hmap.get(index-1))) {

            if (System.currentTimeMillis()>=done_time) {
                newText.setText("TOO LATE");
            }else {
                newText.setText("CORRECT!");
                loadImages();

            }

        }else {
            newText.setText("WRONG");
        }
       // for (int i = 0; i<=10; i++) {

        //    hmap.get(i);

        //}


    }


}
