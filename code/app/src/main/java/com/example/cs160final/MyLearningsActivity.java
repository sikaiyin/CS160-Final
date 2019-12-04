package com.example.cs160final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyLearningsActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    TextView learning1;
    TextView learning2;
    TextView learning3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        learning1 = (TextView)findViewById(R.id.lesson1);
        learning2 = (TextView)findViewById(R.id.lesson2);
        learning3 = (TextView)findViewById(R.id.lesson3);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("users").document(userID);
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    List<String> group = (List<String>) document.get("fLearning");
                    Toast.makeText(MyLearningsActivity.this, group.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    @Override
    protected void onStart()
    {
        super.onStart();

    }
}
