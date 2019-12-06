package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskScreenActivity2Social2Dinner extends AppCompatActivity {

    ImageButton home;
    ImageButton option1;
    ImageButton option2;
    ImageButton option3;
    TextView label1;
    TextView label2;
    TextView label3;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    Date currentTime = Calendar.getInstance().getTime();

    TextView tasklabel;
    TextView explanation;
    ImageButton optionSelected;
    TextView currentLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskscreen2social2dinner);

//        home = (ImageButton)findViewById(R.id.home);
        tasklabel = (TextView)findViewById(R.id.tasklabel);
        explanation = (TextView)findViewById(R.id.note);
        option1 = (ImageButton)findViewById(R.id.option1);
        option2 = (ImageButton)findViewById(R.id.option2);
        option3 = (ImageButton)findViewById(R.id.option3);
        label1 = (TextView)findViewById(R.id.option1_label);
        label2 = (TextView)findViewById(R.id.option2_label);
        label3 = (TextView)findViewById(R.id.option3_label);
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
//                Intent intent = new Intent(TaskScreenActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TaskScreenActivity3Health1Exercise.this, LearningScreenActivity.class);
//                startActivity(intent);
                userID = fAuth.getCurrentUser().getUid();
                final DocumentReference documentReference = fStore.collection("users").document(userID);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                currentLabel=label1;
                                documentReference.update("fOption", FieldValue.arrayUnion(currentLabel.getText() + "\n At  " + currentTime.toString()));
                                documentReference.update("fCurrentBalance", FieldValue.arrayUnion(Integer.toString(Integer.parseInt(document.get("fBudget").toString()) - 10)));
                                documentReference.update("fBudget", Integer.toString(Integer.parseInt(document.get("fBudget").toString()) - 10));
                                documentReference.update("fAcademics", Integer.toString(Integer.parseInt(document.get("fAcademics").toString()) - 0));
                                documentReference.update("fSocial", Integer.toString(Integer.parseInt(document.get("fSocial").toString()) + 3));
                                documentReference.update("fHealth", Integer.toString(Integer.parseInt(document.get("fHealth").toString()) + 3))
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(TaskScreenActivity2Social2Dinner.this, "Updated Successfully",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(TaskScreenActivity2Social2Dinner.this, "Unable to Update",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Intent intent = new Intent(TaskScreenActivity2Social2Dinner.this, LearningScreenActivity2Social2Dinner.class);
                                        startActivity(intent);
                                    }
                                });

                            }
                        }
                    }
                });

            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TaskScreenActivity3Health1Exercise.this, LearningScreenActivity.class);
//                startActivity(intent);
                userID = fAuth.getCurrentUser().getUid();
                final DocumentReference documentReference = fStore.collection("users").document(userID);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                currentLabel=label2;
                                documentReference.update("fOption", FieldValue.arrayUnion(currentLabel.getText() + "\n At  " + currentTime.toString()));
                                documentReference.update("fCurrentBalance", FieldValue.arrayUnion(Integer.toString(Integer.parseInt(document.get("fBudget").toString()) - 15)));
                                documentReference.update("fBudget", Integer.toString(Integer.parseInt(document.get("fBudget").toString()) - 15));
                                documentReference.update("fAcademics", Integer.toString(Integer.parseInt(document.get("fAcademics").toString()) - 0));
                                documentReference.update("fSocial", Integer.toString(Integer.parseInt(document.get("fSocial").toString()) + 3));
                                documentReference.update("fHealth", Integer.toString(Integer.parseInt(document.get("fHealth").toString()) + 0));
                                documentReference.update("fHobbies", Integer.toString(Integer.parseInt(document.get("fHobbies").toString()) + 2))
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(TaskScreenActivity2Social2Dinner.this, "Updated Successfully",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(TaskScreenActivity2Social2Dinner.this, "Unable to Update",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Intent intent = new Intent(TaskScreenActivity2Social2Dinner.this, LearningScreenActivity2Social2Dinner.class);
                                        startActivity(intent);
                                    }
                                });

                            }
                        }
                    }
                });

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TaskScreenActivity3Health1Exercise.this, LearningScreenActivity.class);
//                startActivity(intent);
                userID = fAuth.getCurrentUser().getUid();
                final DocumentReference documentReference = fStore.collection("users").document(userID);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                currentLabel=label3;
                                documentReference.update("fOption", FieldValue.arrayUnion(currentLabel.getText() + "\n At  " + currentTime.toString()));
                                documentReference.update("fCurrentBalance", FieldValue.arrayUnion(Integer.toString(Integer.parseInt(document.get("fBudget").toString()) - 30)));
                                documentReference.update("fBudget", Integer.toString(Integer.parseInt(document.get("fBudget").toString()) - 30));
                                documentReference.update("fAcademics", Integer.toString(Integer.parseInt(document.get("fAcademics").toString()) - 0));
                                documentReference.update("fSocial", Integer.toString(Integer.parseInt(document.get("fSocial").toString()) + 3));
                                documentReference.update("fHobbies", Integer.toString(Integer.parseInt(document.get("fHobbies").toString()) + 5))
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(TaskScreenActivity2Social2Dinner.this, "Updated Successfully",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(TaskScreenActivity2Social2Dinner.this, "Unable to Update",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Intent intent = new Intent(TaskScreenActivity2Social2Dinner.this, LearningScreenActivity2Social2Dinner.class);
                                        startActivity(intent);
                                    }
                                });

                            }
                        }
                    }
                });

            }
        });
    };

    @Override
    public void onBackPressed() {
        // do nothing
    }
}