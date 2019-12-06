package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class TasksOptionsActivity3Health extends AppCompatActivity {

    ImageButton home;
    ImageButton task1;
    ImageButton task2;
    ImageButton task3;
    ImageButton task4;
    ImageButton task5;
    ImageButton task6;
    ImageButton task7;
    ImageButton task8;
    TextView tasklabel1;
    TextView tasklabel2;
    TextView tasklabel3;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    List<String> mTasks;



    TextView tasklabel;
    TextView note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasksoption3health);

//        home = (ImageButton)findViewById(R.id.home);
        task1 = (ImageButton)findViewById(R.id.task1);
        task2 = (ImageButton)findViewById(R.id.task2);
        task3 = (ImageButton)findViewById(R.id.task3);
//        task4 = (ImageButton)findViewById(R.id.task4);
//        task5 = (ImageButton)findViewById(R.id.task5);
//        task6 = (ImageButton)findViewById(R.id.task6);
//        task7 = (ImageButton)findViewById(R.id.task7);
//        task8 = (ImageButton)findViewById(R.id.task8);
//        task1 = (ImageButton)findViewById(R.id.task1);
        tasklabel = (TextView)findViewById(R.id.tasklabel);
        note = (TextView)findViewById(R.id.note);
        tasklabel1 = (TextView)findViewById(R.id.taskOption1_label);
        tasklabel2 = (TextView)findViewById(R.id.taskOption2_label);
        tasklabel3 = (TextView)findViewById(R.id.taskOption3_label);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

    }

    @Override
    protected void onStart()
    {
        super.onStart();

//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TasksOptionsActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

//        task3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userID = fAuth.getCurrentUser().getUid();
//                final DocumentReference documentReference = fStore.collection("users").document(userID);
//                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            final DocumentSnapshot document = task.getResult();
//                            if (document.exists()) {
//                                mTasks = (List<String>) document.get("fTaskList");
//                                if (mTasks.contains(tasklabel1.getText().toString())) {
//                                    Toast.makeText(TasksOptionsActivity3Health.this, "Task Already Completed!", Toast.LENGTH_SHORT).show();
//                                }
//                                    documentReference.update("fWeekCounter", Integer.toString(Integer.parseInt(document.get("fWeekCounter").toString()) + 1));
//                                    documentReference.update("fTaskList", FieldValue.arrayUnion(tasklabel1.getText().toString()));
//                                    Intent intent = new Intent(TasksOptionsActivity3Health.this, TaskScreenActivity3Health1Exercise.class);
//                                    startActivity(intent);
//
//                            }
//
//                        }
//                    }
//                });
//            }
//        });

        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = fAuth.getCurrentUser().getUid();
                final DocumentReference documentReference = fStore.collection("users").document(userID);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            final DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                mTasks = (List<String>) document.get("fTaskList");
                                if (mTasks.contains(tasklabel1.getText().toString())) {
//                                    Toast.makeText(TasksOptionsActivity.this, "Task Already Completed!", Toast.LENGTH_SHORT).show();

                                    // Pop-up message when a task is selected and provide user feedback that it has been completed
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TasksOptionsActivity3Health.this);

                                    builder.setCancelable(true);
                                    builder.setTitle("Task Already Completed!");
                                    builder.setMessage("Please choose another task option.");

                                    ImageView image = new ImageView(TasksOptionsActivity3Health.this);
                                    image.setImageResource(R.drawable.ic_warning);

                                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    builder.setView(image);
                                    builder.show();

                                }
//                                else {
//                                    documentReference.update("fWeekCounter", Integer.toString(Integer.parseInt(document.get("fWeekCounter").toString()) + 1));
//                                    documentReference.update("fTaskList", FieldValue.arrayUnion(tasklabel1.getText().toString()));
//                                    Intent intent = new Intent(TasksOptionsActivity.this, TaskScreenActivity.class);
//                                    startActivity(intent);
//                                }

                                // TESTING: use this line of code if you wish to by passed Completed Tasks
                                documentReference.update("fWeekCounter", Integer.toString(Integer.parseInt(document.get("fWeekCounter").toString()) + 1));
                                documentReference.update("fTaskList", FieldValue.arrayUnion(tasklabel1.getText().toString()));
                                Intent intent = new Intent(TasksOptionsActivity3Health.this, TaskScreenActivity3Health2Examination.class);
                                startActivity(intent);
                            }

                        }
                    }
                });
            }
        });

        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = fAuth.getCurrentUser().getUid();
                final DocumentReference documentReference = fStore.collection("users").document(userID);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            final DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                mTasks = (List<String>) document.get("fTaskList");
                                if (mTasks.contains(tasklabel2.getText().toString())) {
//                                    Toast.makeText(TasksOptionsActivity.this, "Task Already Completed!", Toast.LENGTH_SHORT).show();

                                    // Pop-up message when a task is selected and provide user feedback that it has been completed
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TasksOptionsActivity3Health.this);

                                    builder.setCancelable(true);
                                    builder.setTitle("Task Already Completed!");
                                    builder.setMessage("Please choose another task option.");

                                    ImageView image = new ImageView(TasksOptionsActivity3Health.this);
                                    image.setImageResource(R.drawable.ic_warning);

                                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    builder.setView(image);
                                    builder.show();

                                }
//                                else {
//                                    documentReference.update("fWeekCounter", Integer.toString(Integer.parseInt(document.get("fWeekCounter").toString()) + 1));
//                                    documentReference.update("fTaskList", FieldValue.arrayUnion(tasklabel1.getText().toString()));
//                                    Intent intent = new Intent(TasksOptionsActivity.this, TaskScreenActivity.class);
//                                    startActivity(intent);
//                                }

                                // TESTING: use this line of code if you wish to by passed Completed Tasks
                                documentReference.update("fWeekCounter", Integer.toString(Integer.parseInt(document.get("fWeekCounter").toString()) + 1));
                                documentReference.update("fTaskList", FieldValue.arrayUnion(tasklabel2.getText().toString()));
                                Intent intent = new Intent(TasksOptionsActivity3Health.this, TaskScreenActivity3Health3Weighing.class);
                                startActivity(intent);
                            }

                        }
                    }
                });
            }
        });

        task3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = fAuth.getCurrentUser().getUid();
                final DocumentReference documentReference = fStore.collection("users").document(userID);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            final DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                mTasks = (List<String>) document.get("fTaskList");
                                if (mTasks.contains(tasklabel3.getText().toString())) {
//                                    Toast.makeText(TasksOptionsActivity.this, "Task Already Completed!", Toast.LENGTH_SHORT).show();

                                    // Pop-up message when a task is selected and provide user feedback that it has been completed
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TasksOptionsActivity3Health.this);

                                    builder.setCancelable(true);
                                    builder.setTitle("Task Already Completed!");
                                    builder.setMessage("Please choose another task option.");

                                    ImageView image = new ImageView(TasksOptionsActivity3Health.this);
                                    image.setImageResource(R.drawable.ic_warning);

                                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    builder.setView(image);
                                    builder.show();

                                }
//                                else {
//                                    documentReference.update("fWeekCounter", Integer.toString(Integer.parseInt(document.get("fWeekCounter").toString()) + 1));
//                                    documentReference.update("fTaskList", FieldValue.arrayUnion(tasklabel1.getText().toString()));
//                                    Intent intent = new Intent(TasksOptionsActivity.this, TaskScreenActivity.class);
//                                    startActivity(intent);
//                                }

                                // TESTING: use this line of code if you wish to by passed Completed Tasks
                                documentReference.update("fWeekCounter", Integer.toString(Integer.parseInt(document.get("fWeekCounter").toString()) + 1));
                                documentReference.update("fTaskList", FieldValue.arrayUnion(tasklabel3.getText().toString()));
                                Intent intent = new Intent(TasksOptionsActivity3Health.this, TaskScreenActivity3Health1Exercise.class);
                                startActivity(intent);
                            }

                        }
                    }
                });
            }
        });
    }
}

