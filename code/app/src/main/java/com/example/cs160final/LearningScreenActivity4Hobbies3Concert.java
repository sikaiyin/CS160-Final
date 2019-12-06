package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileObserver;
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

public class LearningScreenActivity4Hobbies3Concert extends AppCompatActivity {

    ImageButton home;
    TextView learning1;
    TextView learning2;
    TextView learning3;
    ImageView image1;
    ImageView image2;
    ImageView image3;

    TextView tasklabel;
    TextView learninglabel;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learningscreen4hobbies3concert);

        home = (ImageButton) findViewById(R.id.home);
        tasklabel = (TextView) findViewById(R.id.tasklabel);
        learninglabel = (TextView) findViewById(R.id.note);
        learning1 = (TextView) findViewById(R.id.learning1);
        learning2 = (TextView) findViewById(R.id.learning2);
        learning3 = (TextView) findViewById(R.id.learning3);
        image1 = (ImageView) findViewById(R.id.imageIcon1);
        image2 = (ImageView) findViewById(R.id.imageIcon2);
        image3 = (ImageView) findViewById(R.id.imageIcon3);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Pop-up message when a task is selected and provide user feedback that it has been completed
        AlertDialog.Builder builder = new AlertDialog.Builder(LearningScreenActivity4Hobbies3Concert.this);

        builder.setCancelable(true);
        builder.setTitle("Congratulations");
        builder.setMessage("You have completed this task!");

        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.ic_confetti);

        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setView(image);
        builder.show();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = fAuth.getCurrentUser().getUid();
                final DocumentReference documentReference = fStore.collection("users").document(userID);
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                documentReference.update("fLearning", FieldValue.arrayUnion(learning1.getText()));
                                documentReference.update("fLearning", FieldValue.arrayUnion(learning2.getText()));
                                documentReference.update("fLearning", FieldValue.arrayUnion(learning3.getText()));
                                documentReference.update("fGraph", FieldValue.arrayUnion(image1.getTag()));
                                documentReference.update("fGraph", FieldValue.arrayUnion(image2.getTag()));
                                documentReference.update("fGraph", FieldValue.arrayUnion(image3.getTag()));
                                Intent intent = new Intent(LearningScreenActivity4Hobbies3Concert.this, com.example.cs160final.MainActivity.class);
                                startActivity(intent);
                            }
                        }

                    }
                });
            }
        });
    }
}


