package com.example.cs160final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LearningScreenActivity extends AppCompatActivity {

    ImageButton home;
    TextView learning1;
    TextView learning2;
    TextView learning3;
    TextView tasklabel;
    TextView learninglabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learningscreen);

        home = (ImageButton)findViewById(R.id.home);
        tasklabel = (TextView)findViewById(R.id.tasklabel);
        learninglabel = (TextView)findViewById(R.id.note);
        learning1 = (TextView)findViewById(R.id.learning1);
        learning2 = (TextView)findViewById(R.id.learning2);
        learning3 = (TextView)findViewById(R.id.learning3);
    }
    @Override
    protected void onStart()
    {
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearningScreenActivity.this, com.example.cs160final.MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
