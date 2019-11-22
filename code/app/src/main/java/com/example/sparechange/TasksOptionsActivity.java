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

public class TasksOptionsActivity extends AppCompatActivity {

    ImageButton home;
    ImageButton task1;
    ImageButton task2;
    ImageButton task3;
    ImageButton task4;
    ImageButton task5;
    ImageButton task6;
    ImageButton task7;
    ImageButton task8;





    TextView tasklabel;
    TextView note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasksoption);

        home = (ImageButton)findViewById(R.id.home);
        task1 = (ImageButton)findViewById(R.id.task1);
        task2 = (ImageButton)findViewById(R.id.task2);
        task3 = (ImageButton)findViewById(R.id.task3);
        task4 = (ImageButton)findViewById(R.id.task4);
        task5 = (ImageButton)findViewById(R.id.task5);
        task6 = (ImageButton)findViewById(R.id.task6);
        task7 = (ImageButton)findViewById(R.id.task7);
        task8 = (ImageButton)findViewById(R.id.task8);
//        task1 = (ImageButton)findViewById(R.id.task1);
        tasklabel = (TextView)findViewById(R.id.tasklabel);
        note = (TextView)findViewById(R.id.note);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TasksOptionsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TasksOptionsActivity.this, TaskScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
