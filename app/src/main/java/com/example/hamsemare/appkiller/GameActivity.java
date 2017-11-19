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


         newText = (EditText) findViewById(R.id.guess);
         GuessButton = (Button) findViewById(R.id.guessButton);
         GuessButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                checkAnswer();

            }
                                        });


        loadImages();

    }

    private void loadImages() {
        ImageView mImageView;
        mImageView = (ImageView) findViewById(R.id.img1);
       // mImageView.setImageResource(R.drawable.img1level1);
        mImageView.setImageResource(R.drawable.img3level1);
//        mImageView.setImageResource(R.drawable.img4level1);
//        mImageView.setImageResource(R.drawable.img5level1);
//        mImageView.setImageResource(R.drawable.img6level1);





    }

    private void checkAnswer() {

        String Answer = (String) newText.getText().toString();
        String answerUpperCase = Answer.toUpperCase();
        if (answerUpperCase == hmap.get(3)) {
            newText.setText("correct");
        }
       // for (int i = 0; i<=10; i++) {

        //    hmap.get(i);

        //}


    }


}
