package com.example.hamsemare.appkiller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        EditText mEdit = (EditText) findViewById(R.id.edit);
        mEdit.setEnabled(false);
        Button myButton = (Button) findViewById(R.id.ready_button);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHomeFeed();
            }
        });
    }

    private void launchHomeFeed() {
        startActivity(new Intent(this, GameActivity.class));
        finish();
    }

}

