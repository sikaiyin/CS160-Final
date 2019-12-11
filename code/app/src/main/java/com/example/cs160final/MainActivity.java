package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    Button myspendings;
    TextView username;
    Button mylearnings;
    ImageView userpicture;
    TextView termlabel;
    TextView term;
    TextView monthlabel;
    ImageView month;
    TextView weeklabel;
    ImageView week;
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
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    String Counter;
    Integer Week;
    Integer Month;
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;

    TextView budgetamount;
    TextView academicscore;
    TextView socialscore;
    TextView healthscore;
    TextView hobbyscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myspendings = (Button)findViewById(R.id.myspendings);
        username = (TextView)findViewById(R.id.username);
        mylearnings = (Button)findViewById(R.id.mylearnings);
        userpicture = (ImageView)findViewById(R.id.userpicture);
        termlabel = (TextView)findViewById(R.id.termlabel);
        term = (TextView)findViewById(R.id.term);
        monthlabel = (TextView)findViewById(R.id.monthlabel);
        month = (ImageView)findViewById(R.id.month);
        weeklabel = (TextView)findViewById(R.id.weeklabel);
        week = (ImageView) findViewById(R.id.week);
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
        budgetamount = (TextView)findViewById(R.id.budgetamount);
        academicscore = (TextView)findViewById(R.id.academicscore);
        socialscore = (TextView)findViewById(R.id.socialscore);
        healthscore = (TextView)findViewById(R.id.healthscore);
        hobbyscore = (TextView)findViewById(R.id.hobbyscore);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("user_profile");
        mStorageRef = FirebaseStorage.getInstance().getReference("user_profile");


        mStorageRef.child(userId + "." + "jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(userpicture);
            }
        });

        final DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Counter = document.get("fWeekCounter").toString();
                        if(Integer.parseInt(Counter) > 64){
                            Toast.makeText(MainActivity.this, "Congratulations! You passed this Game!", Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(getApplicationContext(), login.class));
                            finish();
                        }else if(Integer.parseInt(Counter) > 32){
                            Counter = Integer.toString(Integer.parseInt(Counter) - 32);
                            term.setText("Spring");
                            Week = (Integer.parseInt(Counter) / 2) % 4 + 1;
                            Month = (Integer.parseInt(Counter) / 2) / 4 + 1;
                            int id_week = getResources().getIdentifier("com.example.cs160final:drawable/ic_calendar_week_" + Week.toString() + "_80dp", null, null);
                            week.setImageResource(id_week);
                            int id_month = getResources().getIdentifier("com.example.cs160final:drawable/ic_calendar_month_" + Month.toString() + "_80dp", null, null);
                            month.setImageResource(id_month);
                        }else {
                            term.setText("Fall");
                            Week = (Integer.parseInt(Counter) / 2) % 4 + 1;
                            Month = (Integer.parseInt(Counter) / 2) / 4 + 1;
                            int id_week = getResources().getIdentifier("com.example.cs160final:drawable/ic_calendar_week_" + Week.toString() + "_80dp", null, null);
                            week.setImageResource(id_week);
                            int id_month = getResources().getIdentifier("com.example.cs160final:drawable/ic_calendar_month_" + Month.toString() + "_80dp", null, null);
                            month.setImageResource(id_month);
                        }
                        if(Integer.parseInt(document.get("fBudget").toString()) < 0 ||
                                Integer.parseInt(document.get("fAcademics").toString()) < 0 ||
                                Integer.parseInt(document.get("fSocial").toString()) < 0 ||
                                Integer.parseInt(document.get("fHealth").toString()) < 0 ||
                                Integer.parseInt(document.get("fHobbies").toString()) < 0 )
                        {
                            Toast.makeText(MainActivity.this, "Game Over",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(getApplicationContext(), login.class));
                            finish();
                        }
                        yearofstudy.setText(document.get("fGrade").toString());
                        username.setText(document.get("fName").toString());
                        pbbudget.setProgress((int)(Integer.parseInt(document.get("fBudget").toString()) / 10));
                        pbacademic.setProgress(Integer.parseInt(document.get("fAcademics").toString()));
                        pbsocial.setProgress(Integer.parseInt(document.get("fSocial").toString()));
                        pbhealth.setProgress(Integer.parseInt(document.get("fHealth").toString()));
                        pbentertain.setProgress(Integer.parseInt(document.get("fHobbies").toString()));

                        // Displaying scores/stats of progress bars for user
                        budgetamount.setText("$" + (document.get("fBudget").toString()));
                        academicscore.setText(document.get("fAcademics").toString() + "%");
                        socialscore.setText(document.get("fSocial").toString() + "%");
                        healthscore.setText(document.get("fHealth").toString() + "%");

                        // To cap or limit the percentages/scores to 100 and no more than 100
                        if(Integer.parseInt(document.get("fBudget").toString()) > 1000){
                            documentReference.update("fBudget", "1000");
                            budgetamount.setText("$1000");
                        }else{
                            budgetamount.setText("$" + (document.get("fBudget").toString()));
                        }

                        if(Integer.parseInt(document.get("fAcademics").toString()) > 100){
                            documentReference.update("fAcademics", "100");
                            academicscore.setText("100%");
                        }else{
                            academicscore.setText(document.get("fAcademics").toString() + "%");
                        }
                        if(Integer.parseInt(document.get("fSocial").toString()) > 100){
                            documentReference.update("fSocial", "100");
                            socialscore.setText("100%");
                        }else{
                            socialscore.setText(document.get("fSocial").toString() + "%");
                        }
                        if(Integer.parseInt(document.get("fHealth").toString()) > 100){
                            documentReference.update("fHealth", "100");
                            healthscore.setText("100%");
                        }else{
                            healthscore.setText(document.get("fHealth").toString() + "%");
                        }
                        if(Integer.parseInt(document.get("fHobbies").toString()) > 100){
                            documentReference.update("fHobbies", "100");
                            hobbyscore.setText("100%");
                        }else{
                            hobbyscore.setText(document.get("fHobbies").toString() + "%");
                        }
                        Log.d("month_week_count", Counter);

                    }
                }
            }
        });

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
                // Game rules pop-up
                if (Counter.equals("0")) {

                    int videoGameUnicode = 0x1F3AE;

                    // Pop-up message when a task is selected and provide user feedback that it has been completed
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setCancelable(false);

                    ImageView image = new ImageView(MainActivity.this);
                    image.setImageResource(R.drawable.ic_gamerules);

                    builder.setTitle(new String(Character.toChars(videoGameUnicode)) + " Game Rules " + new String(Character.toChars(videoGameUnicode)));
                    builder.setMessage("1. Completing 2 tasks advances you to the next week. \n\n" +
                            "2. Midterm week: Month 2, Week 4 \n\n" + "3. Finals week: Month 4, Week 4 \n\n" +
                            "4. During exam week, any tasks in the non-Academic category that decrement Academic score will be doubled. \n");


                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setView(image);
                    builder.show();
                }
                // Midterm week pop-up
                else if (Counter.equals("14") || Counter.equals("15")) {

                    int videoGameUnicode = 0x1F640;

                    // Pop-up message when a task is selected and provide user feedback that it has been completed
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setCancelable(false);

                    ImageView image = new ImageView(MainActivity.this);
                    image.setImageResource(R.drawable.ic_exam);

                    builder.setTitle(new String(Character.toChars(videoGameUnicode)) + " MIDTERM WEEK " + new String(Character.toChars(videoGameUnicode)));
                    builder.setMessage("For this week, any tasks in the non-Academic category that decrement Academic score will be doubled. \n");


                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setView(image);
                    builder.show();
                }
                // Final week pop-up
                else if (Counter.equals("30") || Counter.equals("31")) {

                    int videoGameUnicode = 0x1F640;

                    // Pop-up message when a task is selected and provide user feedback that it has been completed
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setCancelable(false);

                    ImageView image = new ImageView(MainActivity.this);
                    image.setImageResource(R.drawable.ic_exam);

                    builder.setTitle(new String(Character.toChars(videoGameUnicode)) + " FINALS WEEK " + new String(Character.toChars(videoGameUnicode)));
                    builder.setMessage("For this week, any tasks in the non-Academic category that decrement Academic score will be doubled. \n");


                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setView(image);
                    builder.show();
                }
                // Any other week that is not week 0, Midterm week, and Finals week
                else {
                    Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    startActivity(intent);
                }
            }
        });
        myspendings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.cs160final.transaction.class);
                startActivity(intent);

            }
        });
        mylearnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.cs160final.list.class);
                startActivity(intent);

            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), login.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }

}
