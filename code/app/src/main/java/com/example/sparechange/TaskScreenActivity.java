package com.example.sparechange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TaskScreenActivity extends AppCompatActivity {

    ImageButton home;
    ImageButton option1;
    ImageButton option2;
    ImageButton option3;






    TextView tasklabel;
    TextView explanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskscreen);

        home = (ImageButton)findViewById(R.id.home);
        tasklabel = (TextView)findViewById(R.id.tasklabel);
        explanation = (TextView)findViewById(R.id.note);
        option1 = (ImageButton)findViewById(R.id.option1);
        option2 = (ImageButton)findViewById(R.id.option2);
        option3 = (ImageButton)findViewById(R.id.option3);

    }
    @Override
    protected void onStart()
    {
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskScreenActivity.this, LearningScreenActivity.class);
                startActivity(intent);
            }
        });

    }
}
