package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TaskScreenActivity extends AppCompatActivity {

    ImageButton home;
    ImageButton option1;
    ImageButton option2;
    ImageButton option3;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

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
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

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
                userID = fAuth.getCurrentUser().getUid();
                final DocumentReference documentReference = fStore.collection("users").document(userID);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            final DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                documentReference.update("fBudget", Integer.toString(Integer.parseInt(document.get("fBudget").toString()) - 10));
                                documentReference.update("fAcademics", Integer.toString(Integer.parseInt(document.get("fAcademics").toString()) + 20));
                                documentReference.update("fSocial", Integer.toString(Integer.parseInt(document.get("fSocial").toString()) - 30));
                                documentReference.update("fHealth", Integer.toString(Integer.parseInt(document.get("fHealth").toString()) - 10))
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(TaskScreenActivity.this, "Updated Successfully",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(TaskScreenActivity.this, "Unable to Update",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(Integer.parseInt(document.get("fBudget").toString()) < 0 ||
                                                Integer.parseInt(document.get("fAcademics").toString()) < 0 ||
                                                Integer.parseInt(document.get("fSocial").toString()) < 0 ||
                                                Integer.parseInt(document.get("fHealth").toString()) < 0){
                                            Toast.makeText(TaskScreenActivity.this, "Game Over",
                                                    Toast.LENGTH_SHORT).show();
                                        }else{
                                        Intent intent = new Intent(TaskScreenActivity.this, LearningScreenActivity.class);
                                        startActivity(intent);}
                                    }
                                });

                            }
                        }
                    }
                });
            }
        });

    }
}
