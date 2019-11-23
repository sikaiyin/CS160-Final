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

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ImageButton myspendings;
    TextView username;
    ImageButton mylearnings;
    ImageView userpicture;
    TextView termlabel;
    TextView term;
    TextView monthlabel;
    TextView month;
    TextView weeklabel;
    TextView week;
    TextView yearofstudy;
    ImageView ivbudget;
    ProgressBar pbbudget;
    ImageView ivacademic;
    ProgressBar pbacademic;
    ImageView ivsocial;
    ProgressBar pbsocial;
    ImageView ivhealth;
    ProgressBar pbhealth;
    ImageView iventertain;
    ProgressBar pbentertain;
    Button performtask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myspendings = (ImageButton)findViewById(R.id.myspendings);
        username = (TextView)findViewById(R.id.username);
        mylearnings = (ImageButton)findViewById(R.id.mylearnings);
        userpicture = (ImageView)findViewById(R.id.userpicture);
        termlabel = (TextView)findViewById(R.id.termlabel);
        term = (TextView)findViewById(R.id.term);
        monthlabel = (TextView)findViewById(R.id.monthlabel);
        month = (TextView)findViewById(R.id.month);
        weeklabel = (TextView)findViewById(R.id.weeklabel);
        week = (TextView)findViewById(R.id.week);
        yearofstudy = (TextView)findViewById(R.id.yearofstudy);
        ivbudget = (ImageView)findViewById(R.id.ivbudget);
        pbbudget = (ProgressBar)findViewById(R.id.pbbudget);
        ivacademic  = (ImageView)findViewById(R.id.ivacademic);
        pbacademic = (ProgressBar)findViewById(R.id.pbacademic);
        ivsocial = (ImageView)findViewById(R.id.ivsocial);
        pbsocial = (ProgressBar)findViewById(R.id.pbsocial);
        ivhealth = (ImageView)findViewById(R.id.ivhealth);
        pbhealth = (ProgressBar)findViewById(R.id.pbhealth);
        iventertain = (ImageView)findViewById(R.id.iventertain);
        pbentertain = (ProgressBar)findViewById(R.id.pbentertain);
        performtask = (Button)findViewById(R.id.performtask);
    }
    @Override
    protected void onStart()
    {
        super.onStart();

        // When you return from the other activities, you may get a difference to update the five scores.

        // You can use
        // int currentprogress=progressbar.getProgress();
        // progressbar.setProgress(currentprogress+diff);
        // to update the score

        // and
        // progressbar.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        // to change the color of the bar

        performtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TasksOptionsActivity.class);
                startActivity(intent);
            }
        });
        myspendings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.cs160final.MySpendingsActivity.class);
                startActivity(intent);
            }
        });
        mylearnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.cs160final.MyLearningsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), login.class));
        finish();
    }
}
