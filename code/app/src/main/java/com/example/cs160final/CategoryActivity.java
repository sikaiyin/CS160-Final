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

public class CategoryActivity extends AppCompatActivity {

    ImageButton home;
    ImageButton taskcategory1;
    ImageButton taskcategory2;
    ImageButton taskcategory3;
    ImageButton taskcategory4;
    ImageButton taskcategory5;
    ImageButton taskcategory6;
    ImageButton taskcategory7;
    ImageButton taskcategory8;





    TextView tasklabel;
    TextView note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//        home = (ImageButton)findViewById(R.id.home);
        taskcategory1 = (ImageButton)findViewById(R.id.taskcategory1);
        taskcategory2 = (ImageButton)findViewById(R.id.taskcategory2);
        taskcategory3 = (ImageButton)findViewById(R.id.taskcategory3);
        taskcategory4 = (ImageButton)findViewById(R.id.taskcategory4);
//        task1 = (ImageButton)findViewById(R.id.task1);
        tasklabel = (TextView)findViewById(R.id.tasklabel);
        note = (TextView)findViewById(R.id.note);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

        taskcategory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, TasksOptionsActivity.class);
                startActivity(intent);
            }
        });
        taskcategory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, TasksOptionsActivity2Social.class);
                startActivity(intent);
            }
        });
        taskcategory3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, TasksOptionsActivity3Health.class);
                startActivity(intent);
            }
        });
        taskcategory4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, TasksOptionsActivity4Hobbies.class);
                startActivity(intent);
            }
        });
    }
}
